module MyIntSetoid

import Setoid
import MyInt

%access public export
%default total

intRefl : Reflx IntEq
intRefl (NewInt a b) = IntRefl $ Refl {x = a + b}

intSym : Sym IntEq
intSym (NewInt a b) (NewInt c d) (IntRefl eq) = IntRefl $ sym eq

reflPlusRefl : {a : Nat} -> {b : Nat} -> {c : Nat} -> {d : Nat} ->
           (a = b) -> (c = d) -> (a + c = b + d)
reflPlusRefl eq1 eq2 = rewrite eq1 in rewrite eq2 in Refl

intTrans : Trans IntEq
intTrans (NewInt a b) (NewInt c d) (NewInt e f) (IntRefl eq1) (IntRefl eq2) = IntRefl rev2
  where

    eq3  : (a + d) + (c + f) = (c + b) + (e + d)
    elD1 : (a + d) + (c + f) = (c + f) + (a + d)
    elD2 : (c + f) + (a + d) = ((c + f) + a) + d
    elD3 : (c + b) + (e + d) = ((c + b) + e) + d
    elD4 : ((c + f) + a) + d = ((c + b) + e) + d
    elD  : (c + f) + a = (c + b) + e
    elC1 : c + (f + a) = (c + b) + e
    elC2 : (f + a) + c = (c + b) + e
    elC3 : (f + a) + c = c + (b + e)
    elC4 : (f + a) + c = (b + e) + c
    elC  : f + a = b + e
    rev1 : a + f = b + e
    rev2 : a + f = e + b

    eq3  = reflPlusRefl eq1 eq2
    elD1 = plusCommutative (a + d) (c + f) -- el for eliminate
    elD2 = plusAssociative (c + f) a d
    elD3 = plusAssociative (c + b) e d
    elD4 = trans (sym elD2) $ trans (sym elD1) $ trans eq3 elD3
    elD  = plusRightCancel ((c + f) + a) ((c + b) + e) d elD4
    elC1 = trans (plusAssociative c f a) elD
    elC2 = trans (plusCommutative (f + a) c) elC1
    elC3 = trans elC2 $ sym $ plusAssociative c b e
    elC4 = trans elC3 $ plusCommutative c (b + e)
    elC  = plusRightCancel (f + a) (b + e) c elC4
    rev1 = trans (plusCommutative a f) elC
    rev2 = trans rev1 $ plusCommutative b e

||| Setoid of MyInt
MyIntSetoid : Setoid
MyIntSetoid = MkSetoid MyInt IntEq $ EqProof IntEq intRefl intSym intTrans