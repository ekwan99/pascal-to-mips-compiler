	.data
	nL:    .asciiz    "\n"
	.text
	.globl main
	main: 
	li $v0 2
	subu $sp $sp 4
	sw $v0 ($sp)
	li $v0 3
	subu $sp $sp 4
	sw $v0 ($sp)
	li $v0 4
	lw $t0 ($sp)
	addu $sp $sp 4
	mult $t0 $v0
	mflo $v0
	lw $t0 ($sp)
	addu $sp $sp 4
	addu $v0 $t0 $v0
	move $a0 $v0
	li $v0 1
	syscall
	la $a0, nL
	li $v0, 4
	syscall
	li $v0 2
	subu $sp $sp 4
	sw $v0 ($sp)
	li $v0 3
	lw $t0 ($sp)
	addu $sp $sp 4
	mult $t0 $v0
	mflo $v0
	subu $sp $sp 4
	sw $v0 ($sp)
	li $v0 4
	lw $t0 ($sp)
	addu $sp $sp 4
	addu $v0 $t0 $v0
	move $a0 $v0
	li $v0 1
	syscall
	la $a0, nL
	li $v0, 4
	syscall
	li $v0 2
	subu $sp $sp 4
	sw $v0 ($sp)
	li $v0 3
	lw $t0 ($sp)
	addu $sp $sp 4
	addu $v0 $t0 $v0
	subu $sp $sp 4
	sw $v0 ($sp)
	li $v0 4
	lw $t0 ($sp)
	addu $sp $sp 4
	mult $t0 $v0
	mflo $v0
	move $a0 $v0
	li $v0 1
	syscall
	la $a0, nL
	li $v0, 4
	syscall
	li $v0 10
	syscall
