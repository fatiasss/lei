.data 
frase: nums



#assuming the string address is in $a0

n_alphas: li $t0, 0 # $t0 = i = 0
          li $t1, 0 # $t1 = alfas = 0
          
for: add $t3, $a0, $t0 #t3 = str[i]
     lb $s0, 0($t3)
     beq $s0, '\0', done
     blt $s0, 'A', not_if
     bgt $s0, 'z', not_if
     addi $t1, $t1, 1
     addi $t0, $t0, 1
     j for
     
not_if: addi $t0, $t0, 1
        j for
        
done: move $v0, $t1
