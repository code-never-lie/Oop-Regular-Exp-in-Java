Regular Expressions
 - Pattrens for recognizing strings
 - way to describe a set of strings based on common characteristics 
 - used for input validation
............................................................
matches function of String class   
    
   "12".matches("[0-9]")   false
   
    "1".matches("[0-9]")   true
    "12".matches("[0-9]*")   true
    "123".matches("[0-9]*") true
example 
   gender (F/M)
   String gender="M";
   gender.macthes("(F|M)")  true   


......................................................
Wild Cards
* zero or more letters
+ one or more letters
? zero or one letter

.......................................................
Example: One or more Digits
 [0-9]*
    
    
class RE {
String regularExp="[0-9]*";
boolean validate(String input){
 if (input.matches(regularExp))
    return true;
 else
    return false;
}
}
class Test{
public static void main(String o[]){
 RE re= new RE();
 if (re.validate(o[0]))
  System.out.println(" OK");
 else
   System.out.println("Not Matched");
}
}
.....................................................................
Meta Characters
 *  zero or more alphabets
 +  one or more alphabets
 ?  zero or one alphabets
 .  any Alphabet
...................................................................
Examples
 a*    null  a  aaa  aaaaa    aaaa
 a+    a aa aaa
 a?    null  a
 .     a b 1 2 + ;   (any one letter) 
 c.t    cat  cbt cxt
 c\.t   c.t
...............................
Characters Classes

1- Simple Class

 [abc]    a , b or C
 
 [bcr]at  accept bat, cat and rat

2- Range
 [a-z]    a to z range
 [a-zA-Z] lower and upper a to z

3- Negation

 [^abc]  all except a,b and c
 [^bcr]at will no accept bat,cat and rat

4-unions (OR)

  [1-4[7-9]]  one to four or 7 to 9
      match   1  2  3  4     7  8  9 
      not match 5  6  90  
5-InterSection (and) 

  [1-7&&[348]]
      match  3     4 
      not match    7    8   1  2  5 
6- Subtraction
  [1-7&&[^347]]   one to seven excluding 3 4 7
      match  1   2  5   6  
      not match    3   4  7

Re for IP  (0-255 Range)
([01]\d?\d?)|(2[01234][0-9])|(25[0-5])


 


..................................................................
Predefined Charecter classes

1- \d    mean [0-9]

2- \D     mean all except 0 to 9  [^0-9]
  
3- \w     mean [a-zA-Z0-9]

4- \W     mean all except [a-zA-Z0-9]

5- \s    A whitespace character: [ \t\n\x0B\f\r]

6- \S      A non-whitespace character: [^\s]

....................................................................
Quantifiers

+
*
?
{}

a      exactly onen a
a{6}  exactly 6 a's

a{n,} at least n a's
a{6,}
\w{6,}
a{n,m} at least n but not more than m times

(?i)(BSCS)  case Insensitive    Accept   bscs BSCS bsCS
...................................................................
Group

(apple){3,}  at least three or more apples
.................................................................
Backreferences

A backreference is specified in the regular expression as a backslash (\) followed by a digit indicating the number of the group to be recalled. For example, the expression (\d\d) defines one capturing group matching two digits in a row, which can be recalled later in the expression via the backreference \1.

Example To match any 2 digits, followed by the exact same two digits

(\d\d)\1

match   1212
not matched 1234
....................................................................... 
Boundary Matchers

maybe you're interested in finding a particular word, but only if it appears at the beginning or end of a line. 
Or maybe you want to know if the match is taking place on a word boundary or at the end of the previous match.

^  The beginning of a line

$  The end of a line

\b A word boundary

\B A non-word boundary

\A The beginning of the input

\G The end of the previous match

\Z The end of the input but for the final terminator, if any

\z The end of the input

................................................................................
Example1 
Validate input base on following three textFields
UCPId  (UCP standard Student Id e.g. L1F09BSCS0015, L2F12BBA1234, l1R12MBA1240)
name   (should contains Alphabets or space)
CGpa   (max 4, Two decimal Places)


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class MyFrame extends JFrame implements ActionListener{
MyFrame() {
setLayout(new FlowLayout());
l1= new Label("UCPId:");
l2= new Label("Name:");
l3= new Label("CGPA:");
l4= new Label("                  ");
id=new TextField(20);
name=new TextField(20);
cgpa=new TextField(20);
b= new Button("Save");
b.addActionListener(this);
add(l1);add(id);add(l2);add(name);
add(l3);add(cgpa);add(b);add(l4);
setSize(260,150);
setVisible(true);
}
public void actionPerformed(ActionEvent e ) {
if (!id.getText().matches(RE[0])){
    l4.setText("Id Incorrect");
    return;
}
if (!name.getText().matches(RE[1])){
    l4.setText("name Incorrect");
    return;
}
if (!cgpa.getText().matches(RE[2])){
    l4.setText("cgpa Incorrect");
    return;
}
l4.setText("All Ok");

}
String RE[]={"[Ll][1-9][FRSfrs][0-9][0-9](?i)((bscs)|(bba)|(mba)|(mscs))[0-9][0-9][0-9][0-9]",
		"[a-zA-Z ]+","(([0-3]\\.[0-9][0-9]?)|(4\\.0?))"};
Label l1,l2,l3,l4;
TextField id;
TextField name;
TextField cgpa;
Button b;
}

class Test{
public static void main(String o[]){
MyFrame mf=new MyFrame();
}
}
.................................................................................
Example 2 : Save record into Database after all Validations(through regular Expressions)

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
class MyFrame extends JFrame implements ActionListener{
MyFrame() {
db= new DB();
setLayout(new FlowLayout());
l1= new Label("UCPId:");
l2= new Label("Name:");
l3= new Label("CGPA:");
l4= new Label("                  ");
id=new TextField(20);
name=new TextField(20);
cgpa=new TextField(20);
b= new Button("Save");
b.addActionListener(this);
add(l1);add(id);add(l2);add(name);
add(l3);add(cgpa);add(b);add(l4);
setSize(260,150);
setVisible(true);
}
public void actionPerformed(ActionEvent e ) {
String ids=id.getText();;
String names=name.getText();
String cgpas=cgpa.getText();
if (!ids.matches(RE[0])){
    l4.setText("Id Incorrect");
    return;
}
if (!names.matches(RE[1])){
    l4.setText("name Incorrect");
    return;
}
if (!cgpas.matches(RE[2])){
    l4.setText("cgpa Incorrect");
    return;
}
if (db.insertDB(ids,names,cgpas))
    l4.setText("Saved");
else
    l4.setText("Duplicate Id");


}
String RE[]={"[Ll][1-9][FRSfrs][0-9][0-9](?i)((bscs)|(bba)|(mba)|(mscs))[0-9][0-9][0-9][0-9]",
		"[a-zA-Z ]+","(([0-3]\\.[0-9][0-9]?)|(4\\.0?))"};
Label l1,l2,l3,l4;
TextField id;
TextField name;
TextField cgpa;
Button b;
DB db;
}

class DB{
public DB () {
 try{
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	con=DriverManager.getConnection("jdbc:odbc:Lahore");
    } catch(Exception e ) 
	{System.out.println(e);	 System.exit(0);}
}
boolean insertDB(String id,String name,String cgpa){
 String qry="insert into student values(?,?,?)";
 PreparedStatement stmt;
try{
 stmt=con.prepareStatement(qry);
 stmt.setString(1,id);
 stmt.setString(2,name);
 stmt.setString(3,cgpa);
 stmt.executeUpdate();
 con.commit();
}catch (Exception e ) {System.out.println(e); return false;}
return true;
}
public Connection getConnection () { return con;}

private Connection con;
}
class Test{
 public static void main(String o[]){
     MyFrame mf=new MyFrame();
 }
}
..........................................................................
Example 3 : Test your  Regular Expression


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class MyFrame extends JFrame implements ActionListener{
MyFrame() {
setLayout(new FlowLayout());
l1= new Label("Regular Expression:");
l2= new Label("Input String      :");
l3= new Label("Result            :");
re=new TextField(40);
input=new TextField(20);
result=new TextField(20);
b= new Button("Verify");
b.addActionListener(this);
add(l1);add(re);add(l2);add(input);
add(l3);add(result);add(b);
setSize(350,200);
setVisible(true);
}
public void actionPerformed(ActionEvent e ) {
if (input.getText().matches(re.getText()))
    result.setText("matched");
else
   result.setText("not  matched");
}
Label l1,l2,l3;
TextField re;
TextField input;
TextField result;
Button b;
}

class Test{
public static void main(String o[]){
MyFrame mf=new MyFrame();
}
}

.................................................................................
Java regx API
- regular expression syntax supported by the java.util.regex API
-The java.util.regex package primarily consists of three classes: 
  1) Pattern
  2) Matcher
  3) PatternSyntaxException

Pattern Class
-A Pattern object is a compiled representation of a regular expression.
-The Pattern class provides no public constructors.
-To create a pattern, you must first invoke one of its public static compile methods, which will then return a Pattern object. 
- These methods accept a regular expression as the first argument

Matcher Class
- Matcher object is the engine that interprets the pattern and performs match operations against an input string.
- Like the Pattern class, Matcher defines no public constructors.
- You obtain a Matcher object by invoking the matcher method on a Pattern object.

PattenSyntaxException

A PatternSyntaxException object is an unchecked exception that indicates a syntax error in a regular expression pattern.


Pattern pattern =Pattern.compile("[0-9][0-9]");

Matcher matcher = pattern.matcher("12");
if (matcher.find()) 
   System.out.println("Matched");
else
   System.out.println("Not Matched");

........................................................................................................................
Example 4: Check your Regular Expression from console input.
Note : Console Class Requires JDk1.6 or Higher

import java.io.Console;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Test {

  public static void main(String[] args){
    Console console = System.console();
    if (console == null) {
      System.err.println("No console.");
      System.exit(1);
    }
    while (true) {

      Pattern pattern =
      Pattern.compile(console.readLine("%nEnter your  regex: "));

      Matcher matcher =
      pattern.matcher(console.readLine("Enter input string to search: "));

      boolean found = false;
      while (matcher.find()) {
        console.format("I found the text \"%s\" starting " +
                               "at index %d and ending at " +
                               "index %d.%n", matcher.group(),
                               matcher.start(), matcher.end());
        found = true;
      }
      if(!found){
        console.format("No match found.%n");
      }
    }
  }
}

......................................................................................................
Output

Enter your regex: [bcr]at
Enter input string to search: bat
I found the text "bat" starting at index 0 and ending at index 3.

Enter your regex: [bcr]at
Enter input string to search: cat
I found the text "cat" starting at index 0 and ending at index 3.

Enter your regex: [bcr]at
Enter input string to search: rat
I found the text "rat" starting at index 0 and ending at index 3.

Enter your regex: [bcr]at
Enter input string to search: hat
No match found.

Enter your regex: foo
Enter input string to search: foofoofoo
I found the text "foo" starting at index 0 and ending at index 3.
I found the text "foo" starting at index 3 and ending at index 6.
I found the text "foo" starting at index 6 and ending at index 9.

Enter your regex: [a-c]
Enter input string to search: a
I found the text "a" starting at index 0 and ending at index 1.

Enter your regex: foo[^1-5]
Enter input string to search: foo6
I found the text "foo6" starting at index 0 and ending at index 4.
Enter your regex: foo[^1-5]
Enter input string to search: foo1
No match found.

Enter your regex: [0-4[6-8]]
Enter input string to search: 0
I found the text "0" starting at index 0 and ending at index 1.

Enter your regex: [0-4[6-8]]
Enter input string to search: 5
No match found.

Enter your regex: [0-9&&[345]]
Enter input string to search: 3
I found the text "3" starting at index 0 and ending at index 1.

Enter your regex: [0-9&&[345]]
Enter input string to search: 2
No match found.

Enter your regex: [0-9&&[^345]]
Enter input string to search: 2
I found the text "2" starting at index 0 and ending at index 1.

Enter your regex: [0-9&&[^345]]
Enter input string to search: 3
No match found.

Enter your regex: .
Enter input string to search: @
I found the text "@" starting at index 0 and ending at index 1.

Enter your regex: \d
Enter input string to search: 1
I found the text "1" starting at index 0 and ending at index 1.

Enter your regex: \d
Enter input string to search: a
No match found.

Enter your regex: a?
Enter input string to search:
I found the text "" starting at index 0 and ending at index 0.

Enter your regex: a*
Enter input string to search:
I found the text "" starting at index 0 and ending at index 0.

Enter your regex: a+
Enter input string to search:
No match found.

Enter your regex: a?
Enter input string to search: aaaaa
I found the text "a" starting at index 0 and ending at index 1.
I found the text "a" starting at index 1 and ending at index 2.
I found the text "a" starting at index 2 and ending at index 3.
I found the text "a" starting at index 3 and ending at index 4.
I found the text "a" starting at index 4 and ending at index 5.
I found the text "" starting at index 5 and ending at index 5.

Enter your regex: [abc]{3}
Enter input string to search: abccabaaaccbbbc
I found the text "abc" starting at index 0 and ending at index 3.
I found the text "cab" starting at index 3 and ending at index 6.
I found the text "aaa" starting at index 6 and ending at index 9.
I found the text "ccb" starting at index 9 and ending at index 12.
I found the text "bbc" starting at index 12 and ending at index 15.

Enter your regex: abc{3}
Enter input string to search: abccabaaaccbbbc
No match found.

Enter your regex: (\d\d)\1
Enter input string to search: 1212
I found the text "1212" starting at index 0 and ending at index 4.

Enter your regex: (?i)foo
Enter input string to search: FOOfooFoOfoO
I found the text "FOO" starting at index 0 and ending at index 3.
I found the text "foo" starting at index 3 and ending at index 6.
I found the text "FoO" starting at index 6 and ending at index 9.
I found the text "foO" starting at index 9 and ending at index 12.





..................................................................................................

Example 5 Spliting string based on Regular Expression


import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SplitDemo {

  private static final String REGEX = ":";
  private static final String INPUT
                                = "one:two:three:four:five";

  public static void main(String[] args) {
    Pattern p = Pattern.compile(REGEX);
    String[] items = p.split(INPUT);
    for(String s : items) {
      System.out.println(s);
    }
  }
}
...........................................................................
OUTPUT:

one
two
three
four
five
...............................................................................
Example 6 Spliting string based on Regular Expression

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SplitDemo2 {
  private static final String REGEX = "\\d";
  private static final String INPUT
                                = "one9two4three7four1five";

  public static void main(String[] args) {
    Pattern p = Pattern.compile(REGEX);
    String[] items = p.split(INPUT)
    for(String s : items) {
      System.out.println(s);
    }
  }
}
...................................................................................
OUTPUT:

one
two
three
four
five
........................................................................................................................

Pattern Method Equivalents in java.lang.String

Regular expression support also exists in java.lang.String through several methods that mimic the behavior of java.util.regex.Pattern. 

1-public boolean matches(String regex) 
Tells whether or not this string matches the given regular expression. An invocation of this method of the form str.matches(regex) yields exactly the same result as the expression Pattern.matches(regex, str).


2-public String[] split(String regex, int limit) 
Splits this string around matches of the given regular expression. An invocation of this method of the form str.split(regex, n) yields the same result as the expression Pattern.compile(regex).split(str, n).


3-public String[] split(String regex) 

Splits this string around matches of the given regular expression. This method works the same as if you invoked the two-argument split method with the given expression and a limit argument of zero. Trailing empty strings are not included in the resulting array.


There is also a replace method that replaces one CharSequence with another:


4- public String replace(CharSequence target, CharSequence replacement) 

Replaces each substring of this string that matches the literal target sequence with the specified literal replacement sequence. The replacement proceeds from the beginning of the string to the end, 

for example, replacing "aa" with "b" in the string "aaa" will result in "ba" rather than "ab".
................................................................................................................................


































