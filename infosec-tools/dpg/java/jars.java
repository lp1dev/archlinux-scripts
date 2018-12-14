import java.util.*;

public final class jars
{
    public static HashMap<Integer, String> numbers()
    {
        HashMap<Integer, String> mp = new HashMap<Integer, String>();

        while( mp.size() < 256 )
        {
            mp.put( mp.size(), "2" );
            mp.put( mp.size(), "3" );
            mp.put( mp.size(), "4" );
            mp.put( mp.size(), "5" );
            mp.put( mp.size(), "6" );
            mp.put( mp.size(), "7" );
            mp.put( mp.size(), "8" );
            mp.put( mp.size(), "9" );
        }

        return mp;
    }

    public static HashMap<Integer, String> special()
    {
        HashMap<Integer, String> mp = new HashMap<Integer, String>();

        while( mp.size() < 256 )
        {
            mp.put( mp.size(), "." );
            mp.put( mp.size(), "," );
            mp.put( mp.size(), "!" );
            mp.put( mp.size(), "*" );
            mp.put( mp.size(), "#" );
            mp.put( mp.size(), "&" );
            mp.put( mp.size(), "^" );
            mp.put( mp.size(), "~" );
        }

        return mp;
    }

    public static HashMap<Integer, String> lower()
    {
        HashMap<Integer, String> mp = new HashMap<Integer, String>();

        while( mp.size() < 256 )
        {
            mp.put( mp.size(), "a" );
            mp.put( mp.size(), "b" );
            mp.put( mp.size(), "c" );
            mp.put( mp.size(), "d" );
            mp.put( mp.size(), "e" );
            mp.put( mp.size(), "f" );
            mp.put( mp.size(), "g" );
            mp.put( mp.size(), "h" );
            mp.put( mp.size(), "j" );
            mp.put( mp.size(), "k" );
            mp.put( mp.size(), "m" );
            mp.put( mp.size(), "n" );
            mp.put( mp.size(), "p" );
            mp.put( mp.size(), "r" );
            mp.put( mp.size(), "s" );
            mp.put( mp.size(), "t" );
        }

        return mp;
    }

    public static HashMap<Integer, String> upper()
    {
        HashMap<Integer, String> mp = new HashMap<Integer, String>();

        while( mp.size() < 256 )
        {
            mp.put( mp.size(), "A" );
            mp.put( mp.size(), "B" );
            mp.put( mp.size(), "C" );
            mp.put( mp.size(), "D" );
            mp.put( mp.size(), "E" );
            mp.put( mp.size(), "F" );
            mp.put( mp.size(), "G" );
            mp.put( mp.size(), "H" );
            mp.put( mp.size(), "J" );
            mp.put( mp.size(), "K" );
            mp.put( mp.size(), "M" );
            mp.put( mp.size(), "N" );
            mp.put( mp.size(), "P" );
            mp.put( mp.size(), "R" );
            mp.put( mp.size(), "S" );
            mp.put( mp.size(), "T" );
        }

        return mp;
    }

    public static HashMap<Integer, String> words()
    {
        HashMap<Integer, String> mp = new HashMap<Integer, String>();

        ArrayList<String> word_list = new ArrayList<String>(
            Arrays.asList("and", "ask", "ass", "ape", "ate", "axe", "air", "aim", "ana", "awe", "act", "add", "age", "all", "ant",
            "bat", "ban", "bar", "bed", "bee", "bet", "bit", "bug", "bob", "bot", "boy", "bud", "but",
            "cab", "can", "cap", "cat", "car", "cog", "con", "cop", "cot", "cow", "coy", "cub", "cut", 
            "dad", "dam", "dan", "day", "den", "did", "dig", "dip", "doc", "dog", "don", "dot", "dry", "dug",
            "ear", "eat", "egg", "ego", "elf", "elk", "elm", "end", "eye", "eve",
            "fad", "fan", "far", "fat", "fax", "fig", "fit", "fix", "fly", "few", "foe", "fog", "for", "fur",
            "gag", "gap", "gel", "gem", "get", "god", "goo", "got", "gum", "gun", "gut", "guy", "gym", 
            "hot", "how", "has", "had", "ham", "hat", "him", "her", "hit", "hop",
            "ice", "icy", "ill", "ink", "inn", "ion", "its", "ivy",
            "jam", "jar", "jaw", "jay", "jet", "jim", "joe", "jog", "jot", "joy", "jug",
            "keg", "ken", "key", "kid", "kim", "kit", "kin", 
            "lab", "lad", "lap", "law", "lie", "lee", "let", "lip", "lob", "log", "lot", "low", "lug", 
            "mac", "mag", "map", "man", "mat", "max", "meg", "men", "met", "mom", "moo", "mop", "mow", "mud", "mug", "mut",
            "nab", "nag", "nap", "net", "new", "nip", "nod", "not", "now", "nun", "nut", 
            "oak", "oat", "oar", "off", "oil", "old", "one", "our", "out", "own",
            "pan", "pal", "pam", "pat", "pea", "pen", "pet", "pig", "pit", "pot",
            "rag", "ray", "run", "ram", "ran", "rap", "rat", "rig", "rip", "rob", "ron", "rot",
            "sad", "sag", "sam", "sat", "say", "see", "sex", "set", "she", "shy", "sin", "sir", "sit", "sky", "soy", "sun",
            "tan", "tap", "tar", "tea", "ted", "too", "the", "tim", "tip", "toe", "tom", "toy",
            "wag", "was", "wax", "way", "web", "wee", "wet", "why", "wig", "win", "wow", "won", 
            "yak", "yam", "yap", "yen", "yep", "yes", "yet", "yew", "you", "yum",
            "zag", "zig", "zit", "zap", "zip", "zoo")
        );

        for( String word : word_list )
        {
            mp.put( mp.size(), word );
        }

        return mp;
    }

}

