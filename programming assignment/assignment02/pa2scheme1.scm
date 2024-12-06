;; Function to reverse a list
(define (my-reverse lst)
  (if (null? lst)
      '()
      (append (my-reverse (cdr lst)) (list (car lst)))))

;; Function to combine inverse of two lists
(define (inv_app list1 list2)
  (append (my-reverse list2) (my-reverse list1)))

;; Test cases
(display (inv_app '(1 2 3) '(a b c)))
(newline)
(display (inv_app '(x y z) '(1 2 3)))
(newline)
