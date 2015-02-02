# machinsk-notes
Jonathon Machinski
1195932
CMPUT 301
Assignment1
Feb 2 2015


UML diagrams are located in the doc folder
Video Link:
https://www.youtube.com/watch?v=kGCL2W8BCGQ&feature=youtu.be


The app is a basic android app for android 16/17/18.
The app tracks travel claims the user creates.
Each TravelClaim created has a start date, an end date
a status, a name, and a text description. Each Travel Claim 
has a list of expenses. Each expense has a category, a currency
a amount spent, a description, and a date. Travel claims and
expenses can be added removed as you please. Listed expense and
claims are listed by start date. Claims and expenses are 
automatically saved once applied.

In Progress:
Navagation improvement
Listing expenses unique to claim
Using statuses for claims
Emailing claims
Viewing claim expense details from a Detials screen



Feedback On the Assignment:
I originally tried to use Intents to pass claim and expense 
objects but ran into many issues. I then decided to just use
and modify parts from Abrim Hindle's student picker app. I 
wish the Labs or assignment would have recommended to use his
app as a starting point, so many hours of frustration could 
have been avoid for people list myself who are new to android
and java. For exampple, I a while installing the GSON libraries
and understanding how to implement it, but once the student 
picker code was used the SharedPreference solution was already
implemented.