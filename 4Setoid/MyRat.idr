module MyRat

import MyInt

%access public export
%default total

data MyRat : Type where
    ||| NewRat a b ->  a/(1 + b) 
    NewRat: MyInt -> Nat -> MyRat

implementation Num MyRat where
    (+) (NewRat a b) (NewRat c d) = NewRat (multNat a (S d) + multNat c (S b)) (b + d + b * d)
    
    (*) (NewRat a b) (NewRat c d) = NewRat (a * c) (b + d + b * d)

    fromInteger a = NewRat (fromInteger a) Z

implementation Neg MyRat where
    negate (NewRat a b) = NewRat (negate a) b
    a - b = a + negate b
    abs (NewRat a b) = NewRat (abs a) b

||| Equality on MyRat
data MyRatEq : MyRat -> MyRat -> Type where
    MyRatRefl : (eq : multNat a (S d) `IntEq` multNat c (S b)) -> MyRatEq (NewRat a b) (NewRat c d)

