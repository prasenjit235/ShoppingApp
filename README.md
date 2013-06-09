ShoppingApp
===========

Shopping App With Sales Tax Problem
------------------------------------
Basic sales tax is applicable at a rate of 10% on all goods, except books, food, and medical products that
are exempt. Import duty is an additional sales tax applicable on all imported goods at a rate of 5%, with
no exemptions.

When I purchase items I receive a receipt which lists the name of all the items and their price (including
tax), finishing with the total cost of the items, and the total amounts of sales taxes paid. The rounding
rules for sales tax are that for a tax rate of n%, a shelf price of p contains (np/100 rounded up to the
nearest 0.05) amount of sales tax.

Write an application that prints out the receipt details for these shopping baskets...
INPUT:

Input 1:
1 book at 12.49
1 music CD at 14.99
1 chocolate bar at 0.85

Input 2:
1 imported box of chocolates at 10.00
1 imported bottle of perfume at 47.50

Input 3:
1 imported bottle of perfume at 27.99
1 bottle of perfume at 18.99

1 packet of headache pills at 9.75
1 box of imported chocolates at 11.25

OUTPUT

Output 1:
1 book : 12.49
1 music CD: 16.49
1 chocolate bar: 0.85
Sales Taxes: 1.50
Total: 29.83

Output 2:
1 imported box of chocolates: 10.50
1 imported bottle of perfume: 54.65
Sales Taxes: 7.65
Total: 65.15

Output 3:
1 imported bottle of perfume: 32.19
1 bottle of perfume: 20.89
1 packet of headache pills: 9.75
1 imported box of chocolates: 11.85
Sales Taxes: 6.70
Total: 74.68

Solution:
-------------------------------------------------

Shopping App will deal with all the requirements mentioned above. This App will create Shopping carts based on number
of input files. For each Shopping cart , items will be added. Then each Shopping cart will be processed and tax will
be calculated for each item and total cost as well. The total tax will be aggregated for Shopping cart which will be
printed in receipt.


How to use:
-----------

This can be used in two supported ways as of now:

a) Define your own properties file or resuse the existing AppMain.properties file.

1) Add 'SHOPPINGAPP.FILE_NAMES' in property file e.g - SHOPPINGAPP.FILE_NAMES=input/input1,input/input2
observe that the file 'input1' is under 'input' directory.The 'input' directory is under 'resources' directory.
If any input file is under a separate directory , make sure that directory is under 'resources' directory.

2) Add comma separated items in categories 'SHOPPINGAPP.MEDICAL' , 'SHOPPINGAPP.BOOKS','SHOPPINGAPP.FOOD',
'SHOPPINGAPP.OTHERS' . As of now these are the only supported categories.
e.g. SHOPPINGAPP.FOOD=chocolate bar,chocolates 
     SHOPPINGAPP.OTHERS=CD,perfume,pens,combs
     
3) Copy the property file under 'resources' directory.

4) open build.xml file 

5) go to target run and add arguement as follows
    <arg value="0"/>
    <arg value="MyAppMain.properties"/>
    
6) Run the command 'ant main'.

7) The output of each input will be created under 'resources/output/'. 


Note :  Please follow the naming convention of input files i.e input<Number> .

        While defining item in a file please follow the convention. (<Quantity> <item description> at <Unit Price>)
        
b) Passing File names as agrument.

1) open build.xml file 

2) go to target run and add arguement as follows
    <arg value="1"/>
    <arg value="input/input1"/>
    <arg value="input/input2"/>
    
3) Run the command 'ant main'.

4) The output of each input will be created under 'resources/output/'. 

