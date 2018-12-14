// Requires Java 8 or newer

import java.awt.*; 
import java.awt.event.*;
import java.awt.datatransfer.*;
import javax.swing.*; 

import java.nio.CharBuffer;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.*;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.Mac; 
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec; 
 
public class dpg extends JFrame {

    private boolean debug = false;

    private JTextField txtPeek         = new JTextField("", 5);
    private JPasswordField txtSentence = new JPasswordField("", 14);
    private JPasswordField txtWord     = new JPasswordField("", 7);

    private char[] sentence = "".toCharArray();
    private char[] word     = "".toCharArray();

    private String human  = "";
    private String result = "";

    Color maroon = new Color(102, 0, 0);
    Color orange = new Color(255, 102, 0);

    Color[] colors = {maroon, orange,
                      Color.BLUE, Color.BLACK,
                      Color.GREEN, Color.darkGray,
                      Color.PINK, Color.RED,
                      Color.GRAY, Color.YELLOW};

    private int clrPos = 0;

    private final int iterations = 32768;
    private final int key_length_in_bytes = 64;

    private final char[] hex_array = "0123456789ABCDEF".toCharArray();

    // This is a FIFO data structure similar to std::queue in C++
    private Queue<Integer> number_queue = new LinkedList<Integer>();

    /**
    * Method to make passwords easy for humans to read
    **/
    private String readable( String password ) {
        int x = 0;
        String str = "";
        while( x < password.length() )
        {
            str += password.substring(x, x+3) + " ";
            x += 3;
        }
        return str;
    }

    /**
    * Convert byte[] to hex string
    * https://stackoverflow.com/questions/9655181/how-to-convert-a-byte-array-to-a-hex-string-in-java
    **/
    private String byte_array_to_hex(byte[] bytes)
    {
        char[] hexChars = new char[bytes.length * 2];

        for ( int j = 0; j < bytes.length; j++ )
        {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hex_array[v >>> 4];
            hexChars[j * 2 + 1] = hex_array[v & 0x0F];
        }

        return new String(hexChars);
    }

    /**
     * Convert char[] to byte[] and return byte[]
     */
    private byte[] chars_to_bytes(char[] chars) {
        CharBuffer charBuffer = CharBuffer.wrap(chars);
        ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(charBuffer);
        byte[] bytes = Arrays.copyOfRange(byteBuffer.array(),
                byteBuffer.position(), byteBuffer.limit());
        Arrays.fill(charBuffer.array(), '\u0000');
        Arrays.fill(byteBuffer.array(), (byte) 0);
        return bytes;
    }

    /**
    * A method to get 64 random bytes deterministically based on user input
    * And to also fill the queue with numbers
    **/
    private void fill_q(char[] sentence, char[] word) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        // 512 bit key_length (64 bytes). Python and C++ use bytes rather than bits.
        KeySpec ks = new PBEKeySpec(sentence, chars_to_bytes(word), iterations, key_length_in_bytes * 8);

        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        
        if( debug )
        {
            String hex_random_bytes = byte_array_to_hex(skf.generateSecret(ks).getEncoded());
            System.out.println("PBKDF2:  " + hex_random_bytes);
        }

        byte[] random_bytes = skf.generateSecret(ks).getEncoded();

        for(byte b : random_bytes)
        {
            if( debug )
            {
                // & 0xFF ensures the int is unsigned
                System.out.println( ((int) b) & 0xFF ); 
            }

            number_queue.add( ((int) b) & 0xFF );
        }
    }

    /**
     * The GUI
     */
    public dpg () {

        jars j = new jars();

        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());
 
        txtPeek.setEditable(false);
 
        JButton btnBig    = new JButton("Big");
        JButton btnSmall  = new JButton("Small");
        JButton btnPhrase = new JButton("Passphrase");
        JButton btnSee    = new JButton("See it");
        JButton btnClr    = new JButton(" ");

        btnClr.setToolTipText("Pick colors to distinguish multiple instances of DPG.");
        txtPeek.setToolTipText("See the first few characters of the password or passphrase.");
        btnBig.setToolTipText("Generate a big password.");
        btnSmall.setToolTipText("Generate a small password.");
        btnPhrase.setToolTipText("Generate a passphrase.");
        btnSee.setToolTipText("See a readable version of the password or passphrase.");
        txtSentence.setToolTipText("Enter a sentence");
        txtWord.setToolTipText("Enter a word");

        JCheckBox chkSecure = new JCheckBox("Secure");
        JButton   btnView   = new JButton("View");

        chkSecure.setToolTipText("Prevent shoulder surfing and tampering.");
        btnView.setToolTipText("View the sentence and the word.");

        JLabel lblSentence = new JLabel("Sentence");
        JLabel lblWord     = new JLabel("Word");
        lblSentence.setToolTipText("Enter a sentence");
        lblWord.setToolTipText("Enter a word");

        lblSentence.setLabelFor(txtSentence);
        lblWord.setLabelFor(txtWord);

        cp.add(lblSentence);
        cp.add(txtSentence);
        cp.add(lblWord);
        cp.add(txtWord);

        cp.add(txtPeek);

        cp.add(btnBig);
        cp.add(btnSmall);
        cp.add(btnPhrase);
        cp.add(btnSee);
        cp.add(btnClr);

        cp.add(chkSecure);
        cp.add(btnView);

        chkSecure.setEnabled(true);
        chkSecure.setSelected(true);
        btnView.setEnabled(false);

        btnBig.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {

               sentence = txtSentence.getPassword();
               word = txtWord.getPassword();
               result = "";
               number_queue.clear();

               if( debug )
               {
                   System.out.println( "sentence array length: " + sentence.length );
                   System.out.println( "word array length: "     + word.length );
               }

               if( sentence.length == 0 )
               {
                   JOptionPane.showMessageDialog(cp, "The sentence is empty.", "Error", JOptionPane.ERROR_MESSAGE);
                   return;
               }

               if( word.length == 0 )
               {
                   JOptionPane.showMessageDialog(cp, "The word is empty.", "Error", JOptionPane.ERROR_MESSAGE);
                   return;
               }

               try
               {
                   fill_q( sentence, word );

                   if( debug )
                   {
                       System.out.println("number_queue size: " + number_queue.size());
                   }

                   while( result.length() < (24-3) )
                   {
                       result += j.lower().get(number_queue.remove());
                   }
                   result += j.special().get(number_queue.remove());
                   result += j.upper().get(number_queue.remove());
                   result += j.numbers().get(number_queue.remove());
               }

               catch (NoSuchAlgorithmException exc)
               {
                   ;
               }

               catch (InvalidKeySpecException exc)
               {
                   ;
               }

               txtPeek.setText(result.substring(0,4));
               StringSelection stringSelection = new StringSelection(result);
               Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
               clpbrd.setContents(stringSelection, null);
           }
        });

        btnSmall.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {

               sentence = txtSentence.getPassword();
               word = txtWord.getPassword();
               result = "";
               number_queue.clear();

               if( debug )
               {
                   System.out.println( "sentence array length: " + sentence.length );
                   System.out.println( "word array length: "     + word.length );
               }

               if( sentence.length == 0 )
               {
                   JOptionPane.showMessageDialog(cp, "The sentence is empty.", "Error", JOptionPane.ERROR_MESSAGE);
                   return;
               }

               if( word.length == 0 )
               {
                   JOptionPane.showMessageDialog(cp, "The word is empty.", "Error", JOptionPane.ERROR_MESSAGE);
                   return;
               }

               try
               {
                   fill_q( sentence, word );

                   if( debug )
                   {
                       System.out.println("number_queue size: " + number_queue.size());
                   }

                   while( result.length() < (12-3) )
                   {
                       result += j.lower().get(number_queue.remove());
                   }
                   result += j.special().get(number_queue.remove());
                   result += j.upper().get(number_queue.remove());
                   result += j.numbers().get(number_queue.remove());
               }

               catch (NoSuchAlgorithmException exc)
               {
                   ;
               }

               catch (InvalidKeySpecException exc)
               {
                   ;
               }

               txtPeek.setText(result.substring(0,4));
               StringSelection stringSelection = new StringSelection(result);
               Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
               clpbrd.setContents(stringSelection, null);
           }
        });

        btnPhrase.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {

               sentence = txtSentence.getPassword();
               word = txtWord.getPassword();
               result = "";
               number_queue.clear();

               if( debug )
               {
                   System.out.println( "sentence array length: " + sentence.length );
                   System.out.println( "word array length: "     + word.length );
               }

               if( sentence.length == 0 )
               {
                   JOptionPane.showMessageDialog(cp, "The sentence is empty.", "Error", JOptionPane.ERROR_MESSAGE);
                   return;
               }

               if( word.length == 0 )
               {
                   JOptionPane.showMessageDialog(cp, "The word is empty.", "Error", JOptionPane.ERROR_MESSAGE);
                   return;
               }

               try
               {
                   fill_q( sentence, word );

                   if( debug )
                   {
                       System.out.println("number_queue size: " + number_queue.size());
                   }

                   int i = 0;

                   while( i < 7 )
                   {
                       result += j.words().get(number_queue.remove());
                       i++;
                   }
                   result += j.special().get(number_queue.remove());
                   result += j.upper().get(number_queue.remove());
                   result += j.numbers().get(number_queue.remove());
               }

               catch (NoSuchAlgorithmException exc)
               {
                   ;
               }

               catch (InvalidKeySpecException exc)
               {
                   ;
               }

               txtPeek.setText(result.substring(0,4));
               StringSelection stringSelection = new StringSelection(result);
               Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
               clpbrd.setContents(stringSelection, null);
           }
        });

        btnSee.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {

               String sentenceText = new String(txtSentence.getPassword());
               String wordText = new String(txtWord.getPassword());
               human = readable( result );
               JOptionPane.showMessageDialog(cp, "Password:  " + human);
           }
        });

        btnClr.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {

               if( clrPos < colors.length )
               {
                   btnClr.setBackground(colors[clrPos]);
                   clrPos++;
               }
               else
               {
                   btnClr.setBackground(colors[2]);
                   clrPos = 0;
               }
           }
        });

        chkSecure.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {

               if(btnView.isEnabled())
               {
                   btnView.setSelected(false);
                   btnView.setEnabled(false);
               }
               else
               {
                   btnView.setEnabled(true);
                   txtPeek.setText("");
                   txtSentence.setText("");
                   txtWord.setText("");
                   human = "";
                   result = "";

                   StringSelection stringSelection = new StringSelection("");
                   Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                   clpbrd.setContents(stringSelection, null);
               }
           }
        });

        btnView.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {

               String sentenceText = new String(txtSentence.getPassword());
               String wordText = new String(txtWord.getPassword());
               JOptionPane.showMessageDialog(cp, "Sentence: " + sentenceText + "\n" + "Word: " + wordText);
           }
        });
 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setTitle("DPG v1.8");    
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        pack();
        setBounds(0,0,screenSize.width, screenSize.height);
        setLocationRelativeTo(null);
        setSize(475, 200);     
        setVisible(true);     
        setResizable(true);      
    }
 
    /**
     * The main method
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new dpg();
            }
        });
    }
}

