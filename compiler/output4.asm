	.data
	nL:    .asciiz    "\n"
	.text
	.globl main
	main: 
	li $v0 1
	move $a0 $v0
	li $v0 1
	syscall
	la $a0, nL
	li $v0, 4
	syscall
	li $v0 2
	move $a0 $v0
	li $v0 1
	syscall
	la $a0, nL
	li $v0, 4
	syscall
	li $v0 3
	move $a0 $v0
	li $v0 1
	syscall
	la $a0, nL
	li $v0, 4
	syscall
	li $v0 10
	syscall
