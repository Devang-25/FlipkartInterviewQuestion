# InterviewQuestions
This question was asked in Flipkart Machine Coding Round.

FIND CABS FOR BOOKING:

Problem statement:
You have been given list of trips which driver and customer have completed. At the end of the trip driver has given a rating to the customer and the customer has given rating to the driver as well. Ratings can be between 1 to 5. With 5 being a good rating and 1 being bad rating.

Given a customer name, based on the historical trips you need to find the eligible cabs to the customer.
Here are the rules for choosing the eligible drivers:
1) The average rating of the cab driver should be equal or higher than the average rating of the customer.
2) If there are no matches for #1 then you may consider the driver with the customer has ridden before.
3) If a customer has rated a driver with 1 starthen the driver is not eligible for the bookings or vice-versa.

Given a customer name
1) Print the average rating of the customer
2) Print eligible driver names with their average rating

Bonus Question:
1) The driver can go offline as well. Consider eligible drivers with those who are available online.
2) Unit test the code

