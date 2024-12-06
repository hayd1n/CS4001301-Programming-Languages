;; Function to double occurrences of an atom in a list
(define (dbl_atm atom lst)
  (cond 
    ((null? lst) '())
    ((list? (car lst))
     (cons 
      (dbl_atm atom (car lst)) 
      (dbl_atm atom (cdr lst))))
    ((equal? (car lst) atom)
     (cons atom 
           (cons atom 
                 (dbl_atm atom (cdr lst)))))
    (else 
     (cons (car lst) 
           (dbl_atm atom (cdr lst))))))

;; Test cases
(display (dbl_atm 'a '(a (b c a (a d)))))
(newline)
(display (dbl_atm 'x '(a b c x (x y))))
(newline)