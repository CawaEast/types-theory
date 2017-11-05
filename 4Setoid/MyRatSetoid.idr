module MyRatSetoid

import Setoid
import MyRat
import MyIntSetoid
import MyInt

%access public export
%default total

ratReflx : Reflx MyRatEq
ratReflx (NewRat a b) = MyRatRefl (intRefl (multNat a (S b))) 

ratSym : Sym MyRatEq
ratSym (NewRat a b) (NewRat c d) (MyRatRefl eq) = MyRatRefl $ intSym (multNat a (S d)) (multNat c (S b)) eq

ratTrans : Trans MyRatEq
ratTrans (NewRat (NewInt a1 a2) b) (NewRat (NewInt c1 c2) d) (NewRat (NewInt e1 e2) f)
        (MyRatRefl (IntRefl eq1)) (MyRatRefl (IntRefl eq2)) = MyRatRefl $ IntRefl step5
  where
    step1' : (g1 * S y)       + (h2 * S x)       = (h1 * S x)       + (g2 * S y)       ->
             (g1 * S y) * S z + (h2 * S x) * S z = (h1 * S x) * S z + (g2 * S y) * S z
    step1' prf {g1 = g1} {g2 = g2} {h1 = h1} {h2 = h2} {x = x} {y = y} {z = z} =
        rewrite sym $ multDistributesOverPlusLeft (g1 * S y) (h2 * S x) (S z) in
        rewrite sym $ multDistributesOverPlusLeft (h1 * S x) (g2 * S y) (S z) in
        cong {f = (* S z)} prf

    step1'eq1 : (a1 * S d) * S f + (c2 * S b) * S f = (c1 * S b) * S f + (a2 * S d) * S f
    step1'eq1 = step1' eq1
    step1'eq2 : (c1 * S f) * S b + (e2 * S d) * S b = (e1 * S d) * S b + (c2 * S f) * S b
    step1'eq2 = step1' eq2

    step2' : {g : Nat} -> {h : Nat} -> {x : Nat} -> {y : Nat} ->
             (g = h) -> (x = y) -> (g + x = h + y)
    step2' prf1 prf2 = rewrite prf1 in rewrite prf2 in Refl

    step2 : ((a1 * S d) * S f + (c2 * S b) * S f) + ((c1 * S f) * S b + (e2 * S d) * S b) =
            ((c1 * S b) * S f + (a2 * S d) * S f) + ((e1 * S d) * S b + (c2 * S f) * S b)
    step2 = step2' step1'eq1 step1'eq2

    step3 : (a1 * S d) * S f + (e2 * S d) * S b =
            (a2 * S d) * S f + (e1 * S d) * S b
    step3 = rewrite plusCommutative (a1 * S d * S f) (e2 * S d * S b) in int5
      where
        int1Left : ((a1 * S d) * S f + (c2 * S b) * S f) + ((c1 * S f) * S b + (e2 * S d) * S b) =
                   ((c1 * S f) * S b + (e2 * S d) * S b) + ((a1 * S d) * S f + (c2 * S f) * S b)
        int1Left =
            rewrite sym $ multAssociative c2 (S f) (S b) in
            rewrite sym $ multAssociative c2 (S b) (S f) in
            rewrite sym $ multCommutative (S f) (S b) in
            rewrite plusCommutative ((a1 * S d) * S f + c2 * (S (b + f * S b))) ((c1 * S f) * S b + (e2 * S d) * S b) in
            Refl

        int1 : ((c1 * S f) * S b + (e2 * S d) * S b) + (a1 * S d) * S f + (c2 * S f) * S b =
               ((c1 * S b) * S f + (a2 * S d) * S f) + (e1 * S d) * S b + (c2 * S f) * S b
        int1 =
            rewrite sym $ plusAssociative ((c1 * S f) * S b + (e2 * S d) * S b) ((a1 * S d) * S f) ((c2 * S f) * S b) in
            rewrite sym $ plusAssociative ((c1 * S b) * S f + (a2 * S d) * S f) ((e1 * S d) * S b) ((c2 * S f) * S b) in
            rewrite sym $ int1Left in
            step2

        int2 : (c1 * S f) * S b + (e2 * S d) * S b + (a1 * S d) * S f =
               (c1 * S b) * S f + (a2 * S d) * S f + (e1 * S d) * S b
        int2 = plusRightCancel
            ((c1 * S f) * S b + (e2 * S d) * S b + (a1 * S d) * S f)
            ((c1 * S b) * S f + (a2 * S d) * S f + (e1 * S d) * S b)
            (c2 * S f * S b)
            int1

        int3 : (c1 * S f) * S b + ((e2 * S d) * S b + (a1 * S d) * S f) =
               (c1 * S b) * S f + ((a2 * S d) * S f + (e1 * S d) * S b)
        int3 =
            rewrite plusAssociative ((c1 * S f) * S b) ((e2 * S d) * S b) ((a1 * S d) * S f) in
            rewrite plusAssociative ((c1 * S b) * S f) ((a2 * S d) * S f) ((e1 * S d) * S b) in
            int2

        int4Left : (c1 * S b) * S f + ((e2 * S d) * S b + (a1 * S d) * S f) =
                   (c1 * S f) * S b + ((e2 * S d) * S b + (a1 * S d) * S f)
        int4Left =
            rewrite sym $ multAssociative c1 (S b) (S f) in
            rewrite multCommutative (S b) (S f) in
            rewrite multAssociative c1 (S f) (S b) in
            Refl

        int4 : (c1 * S b) * S f + ((e2 * S d) * S b + (a1 * S d) * S f) =
               (c1 * S b) * S f + ((a2 * S d) * S f + (e1 * S d) * S b)
        int4 = rewrite int4Left in int3

        int5 : (e2 * S d) * S b + (a1 * S d) * S f =
               (a2 * S d) * S f + (e1 * S d) * S b
        int5 = plusLeftCancel
            (c1 * S b * S f)
            ((e2 * S d) * S b + (a1 * S d) * S f)
            ((a2 * S d) * S f + (e1 * S d) * S b)
            int4

    step4 : S d * (a1 * S f + e2 * S b) = S d * (a2 * S f + e1 * S b)
    step4 =
        rewrite multDistributesOverPlusRight (S d) (a1 * S f) (e2 * S b) in
        rewrite multDistributesOverPlusRight (S d) (a2 * S f) (e1 * S b) in
        rewrite lemma (S d) a1 (S f) in
        rewrite lemma (S d) a2 (S f) in
        rewrite lemma (S d) e1 (S b) in
        rewrite lemma (S d) e2 (S b) in
        step3
      where
        lemma : (x : Nat) -> (y : Nat) -> (z : Nat) -> x * (y * z) = y * x * z
        lemma x y z =
            rewrite multAssociative x y z in
            rewrite multCommutative x y in
            Refl

    step5 : a1 * S f + e2 * S b = e1 * S b + a2 * S f
    step5 =
        rewrite plusCommutative (e1 * S b) (a2 * S f) in
        multLeftCancel _ _ _ step4
      where
        addensumIsZero : (x : Nat) -> (y : Nat) -> x + y = Z -> x = Z
        addensumIsZero x Z prf = int
          where
            int : Z + x = Z
            int = rewrite plusCommutative Z x in prf
        addensumIsZero x (S k) prf = void $ SIsNotZ int
          where
            int : S k + x = Z
            int = rewrite plusCommutative (S k) x in prf

        multLeftCancel : (x : Nat) -> (y : Nat) -> (z : Nat) -> S x * y = S x * z -> y = z
        multLeftCancel x Z z prf = sym $ addensumIsZero _ _ (sym int)
          where
            int : Z * x = z + (x * z)
            int = rewrite multCommutative Z x in prf
        multLeftCancel x y Z prf = addensumIsZero _ _ int
          where
            int : y + (x * y) = Z * x
            int = rewrite multCommutative Z x in prf
        multLeftCancel x (S k) (S j) prf =
            let rec = multLeftCancel x k j in
            rewrite rec (plusLeftCancel _ _ _ int2) in
            Refl
          where
            prf' : k + x * S k = j + x * S j
            prf' = succInjective _ _ prf

            int1 : k + (x + x * k) = j + (x + x * j)
            int1 =
                rewrite sym $ multRightSuccPlus x k in
                rewrite sym $ multRightSuccPlus x j in
                prf'

            int2 : x + (k + x * k) = x + (j + x * j)
            int2 =
                rewrite plusAssociative x k (x * k) in
                rewrite plusAssociative x j (x * j) in
                rewrite plusCommutative x k in
                rewrite plusCommutative x j in
                rewrite sym $ plusAssociative k x (x * k) in
                rewrite sym $ plusAssociative j x (x * j) in
                int1

||| Setoid of MyRat
MyRatSetoid : Setoid
MyRatSetoid = MkSetoid MyRat MyRatEq $ EqProof MyRatEq ratReflx ratSym ratTrans