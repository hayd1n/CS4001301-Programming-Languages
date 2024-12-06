# Programming Assignment 2

> NTUST Course Homework
> Homework Name: [Programming Assignment #2](https://faculty.csie.ntust.edu.tw/~ywu/cs4001301/PA2.TXT)
>
> Course Name: Programming Languages
> Course No: CS4001301
> Course Teacher: Yi-Leh Wu
>
> Student Name: Hayden Chang 張皓鈞
> Student ID: B11030202

## 1. Scheme

**function name:** `inv_app`

**input:** 2 lists, `list1` and `list2`

**output:** inverse of list2 + inverse of list1

**Example:**

```
list1 = (1 2 3)
list2 = (a b c)

output = (c b a 3 2 1)
```

Put your source code in a file named `pa2scheme1.scm` and include at lease one test case for your implementation.  

### My answer

[Source code](./pa2scheme1.scm)

#### Test cases

```
;; Test cases
(display (inv_app '(1 2 3) '(a b c)))
(newline)
(display (inv_app '(x y z) '(1 2 3)))
(newline)
```

#### Results

```
(c b a 3 2 1)
(3 2 1 z y x)
```



## 2. Scheme

**function name:** `dbl_atm`

**input:** an atom and a list

**output:** a list with all occurrences of the given atom doubled 

Example:

```
atom = a
list2 = (a (b c a (a d)))

output = (a a (b c a a (a a d)))
```

Put your source code in a file named `pa2scheme2.scm` and include at lease one test case for your implementation.

### My answer

[Source code](./pa2scheme2.scm)

#### Test cases

```
;; Test cases
(display (dbl_atm 'a '(a (b c a (a d)))))
(newline)
(display (dbl_atm 'x '(a b c x (x y))))
(newline)
```

#### Results

```
(a a (b c a a (a a d)))
(a b c x x (x x y))
```



## 3. Prolog

All the text below should be cut and pasted into a file called `pa2prolog.pl`. Follow the directions and include any queries requested. 

Complete the following predicates so that they correctly model the relationships between the people listed below as facts.

Show that using your rules, you can infer that adam, rosa and fran are betty's cousins, and that betty and tom are one-another's brother and sister. Also create queries that test all predicates. 

```
mother(X, Y) :-

father(X, Y) :-

child(X, Y) :-

sibling(X, Y) :-

sister(X, Y) :-

brother(X, Y) :-

daughter(X, Y) :-

son(X, Y) :-

uncle(X, Y) :-

aunt(X, Y) :-

cousin(X, Y) :-

% Do not modify the following facts.  Complete the predicates 
% above so that they work with the following facts.

male(mark).
male(mel).
male(richard).
male(tom).
male(adam).

female(amy).
female(jane).
female(joan).
female(betty).
female(rosa).
female(fran).


parent(mel, joan).
parent(jane, betty).
parent(jane, tom).
parent(richard, adam).
parent(richard, rosa).
parent(joan, fran).
parent(mark, jane).
parent(mark, richard).
parent(amy, jane).
parent(amy, richard).
parent(amy, joan).
```

### My answer

[Source code](./pa2prolog.pl)

#### Test cases

```
% Cousin relationships
?- cousin(adam, betty).

?- cousin(rosa, betty).

?- cousin(fran, betty).

% Sibling relationship
?- sibling(betty, tom).
```

#### Results

```
?- cousin(adam, betty).
true

?- cousin(rosa, betty).
true

?- cousin(fran, betty).
true

?- sibling(betty, tom).
true
```

