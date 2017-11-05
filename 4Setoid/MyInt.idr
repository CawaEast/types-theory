module MyInt

%access public export
%default total

||| Integer number
data MyInt =
    ||| NewInt a b represents a - b
    NewInt Nat Nat

implementation Num MyInt where
    -- ((a - b)) + ((c - d)) = (((a + c) - (b + d)))
    (+) (NewInt a b) (NewInt c d) = NewInt (a + c) (b + d)

    -- ((a - b)) * ((c - d)) = (((a * c + b * d) - (a * d + b * c)))
    (*) (NewInt a b) (NewInt c d) = NewInt (a * c + b * d) (a * d + b * c)

    fromInteger n =
        if n > 0 then
            NewInt (fromInteger n) Z
        else if n < 0 then
            NewInt Z (fromInteger (-n))
        else
            NewInt Z Z

implementation Neg MyInt where
    negate (NewInt a b) = NewInt b a
    (NewInt a b) - (NewInt c d) = NewInt (a + d) (b + c)
    abs n@(NewInt a b) = if a < b then NewInt b a else n

||| Multiply MyInt by Nat
multNat : MyInt -> Nat -> MyInt
multNat (NewInt a b) k = NewInt (a * k) (b * k)

||| Equality on MyInt
data IntEq : MyInt -> MyInt -> Type where
    ||| a - b == c - d  <===>  a + d == c + b
    IntRefl : (eq : a + d = c + b) -> IntEq (NewInt a b) (NewInt c d)