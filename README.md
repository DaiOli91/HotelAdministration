# HotelAdmnistration
Software to keep record of the administrative activities for a hotel

This system was designed to keep administrative records for a small hotel. This includes the posibility of registering and editing users, rooms, and bookings.
Rooms can only be rented if booked beforehand. Bookings should be at least 7(seven) days long. Records cannot be deleted, they are deactivated instead (for keeping historical data).
When the system starts, it loads data from json files to lists and the Main menu is called. This Main menu may call additional menues accordingly (depending on the type of logged user).
At the same time, these additional menues can call extra menues to edit data or to query data from the lists.
Logged users can be Passenger, Recepcionist or Manager. They will have different kind of access. To simplify this software, there are hardcored users at disposal for first use.
Also, if managers want to access receptionists or passengers methonds, they will have to access with a different user.
All additional menues return data to the Main menu, which in turn saves the data once again in Json files before exiting the system.
To operate with Json files, the library Gson from Google has been incorporated.

Passenger users will be able to:

[1]. See available rooms
[2]. New Booking
[3]. My Bookings
[4]. Edit Account
[5]. Deactivate Account

Receptionist users will be able to:
[1]. Check In
[2]. Check Out
[3]. See Bookings
[4]. See Booking by User ID
[5]. Cancel Booking
[6]. See Rooms
[7]. Edit Room Availability
[8]. Edit Account

Manager users will be able to:
[1]. Add Employee
[2]. See Users
[3]. Edit Account
[4]. Activate/Deactivate User
[5]. Add Room
[6]. See Rooms
[7]. Edit Room Category
[8]. Activate/Deactivate Room
