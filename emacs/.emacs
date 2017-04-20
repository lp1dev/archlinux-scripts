(add-to-list 'load-path (expand-file-name "~/archlinux-scripts/emacs"))
(setq backup-directory-alist `(("." . "~/.saves")))
(require 'go-mode-autoloads)
(require 'auto-install)
(require 'angular-html-mode)
(require 'ng2-mode)
(require 'tss)
(require 'typescript)
(tss-config-default)

(require 'auto-complete)
(global-auto-complete-mode t)
 
(require 'auto-complete-config)
(ac-ropemacs-initialize)
(global-auto-complete-mode t)
(define-key ac-complete-mode-map "\t" 'ac-expand)
(define-key ac-complete-mode-map "\r" 'ac-complete)
(define-key ac-complete-mode-map "\M-n" 'ac-next)
(define-key ac-complete-mode-map "\M-p" 'ac-previous)
(setq ac-auto-start 3)
(setq ac-dwim t)
(set-default 'ac-sources '(ac-source-abbrev ac-source-words-in-buffer))
(setq ac-modes
      (append ac-modes
	      '(eshell-mode
		)))
