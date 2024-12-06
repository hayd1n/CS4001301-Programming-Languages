% Family Relationships Predicates

% Mother rule
mother(X, Y) :- 
    female(X),
    parent(X, Y).

% Father rule
father(X, Y) :- 
    male(X),
    parent(X, Y).

% Child rule
child(X, Y) :- 
    parent(Y, X).

% Sibling rule
sibling(X, Y) :- 
    parent(Z, X),
    parent(Z, Y),
    X \= Y.

% Sister rule
sister(X, Y) :- 
    female(X),
    sibling(X, Y).

% Brother rule
brother(X, Y) :- 
    male(X),
    sibling(X, Y).

% Daughter rule
daughter(X, Y) :- 
    female(X),
    child(X, Y).

% Son rule
son(X, Y) :- 
    male(X),
    child(X, Y).

% Uncle rule
uncle(X, Y) :- 
    male(X),
    parent(Z, Y),
    sibling(X, Z).

% Aunt rule
aunt(X, Y) :- 
    female(X),
    parent(Z, Y),
    sibling(X, Z).

% Cousin rule
cousin(X, Y) :- 
    parent(A, X),
    parent(B, Y),
    sibling(A, B),
    X \= Y.

% Facts (as provided in the original problem)
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

% Queries to test the relationships
% 1. Test that adam, rosa, and fran are betty's cousins
% ?- cousin(adam, betty).
% ?- cousin(rosa, betty).
% ?- cousin(fran, betty).

% 2. Test that betty and tom are brother and sister
% ?- sibling(betty, tom).