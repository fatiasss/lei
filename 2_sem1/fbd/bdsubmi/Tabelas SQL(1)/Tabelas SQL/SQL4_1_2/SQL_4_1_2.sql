Create Table rsv_voos.Airport(
    Airport_code int Identity(1,1) PRIMARY KEY,
    City varchar(255) NOT NULL,
    [State] varchar(255) NOT NULL,
    [Name] varchar(255) NOT NULL
);

Create Table rsv_voos.Airplane_Type(
    Type_name varchar(255) PRIMARY KEY,
    Max_seats int CHECK (Max_seats > 0),
    Company varchar(255) NOT NULL
);

Create Table rsv_voos.Can_Land(
    Airplane_Type varchar(255) NOT NULL,
    Airport_ID int NOT NULL,
    PRIMARY KEY (Airplane_Type, Airport_ID),
    FOREIGN KEY (Airplane_Type) REFERENCES Airplane_Type(Type_name),
    FOREIGN KEY (Airport_ID) REFERENCES Airport(Airport_code)
);


Create Table rsv_voos.Airplane(
    Airplane_Id int Identity(1,1) PRIMARY KEY,
    Total_no_of_seats int CHECK (Total_no_of_seats > 0),
    Airplane_type_name varchar(255) NOT NULL,
    FOREIGN KEY (Airplane_type_name) REFERENCES Airplane_Type(Type_name)
);

Create Table rsv_voos.Flight(
    [Number] int Identity(1,1) PRIMARY KEY,
    Weekdays varchar(255) NOT NULL,
    Airline varchar(255) NOT NULL
);

Create Table rsv_voos.Fare(
    Code int Identity(1,1) PRIMARY KEY,
    Flight_number int NOT NULL,
    Amount int CHECK (Amount>=0),
    Restrictions varchar(255),
    FOREIGN KEY (Flight_number) REFERENCES Flight([Number])
);

Create Table rsv_voos.Flight_Leg(
    LegNumber int Identity(1,1),
    Flight_number int NOT NULL,
    Dept_Airport int NOT NULL, 
    Dept_Scheduled_Time DATETIME NOT NULL,  
    Arr_Airport int NOT NULL, 
    Arr_Scheduled_Time DATETIME NOT NULL,
    PRIMARY KEY (LegNumber, Flight_number),
    FOREIGN KEY (Flight_number) REFERENCES Flight([Number]),
    FOREIGN KEY (Dept_Airport) REFERENCES Airport(Airport_code),
    FOREIGN KEY (Arr_Airport) REFERENCES Airport(Airport_code),
    CHECK (Dept_Airport != Arr_Airport),
    CHECK (Arr_Scheduled_Time > Dept_Scheduled_Time)
);

Create Table rsv_voos.Leg_Instance(
    [Date] DATE NOT NULL,
    FlightLeg_Flight_number int NOT NULL,
    FlightLeg_LegNumber int NOT NULL,
    No_of_avail_seats int CHECK (No_of_avail_seats >= 0),
    Dept_Airport int,
    Dept_Scheduled_Time DATETIME,
    Arr_Airport int, 
    Arr_Scheduled_Time DATETIME, 
    Airplane_Id int NOT NULL, 
    PRIMARY KEY ([Date], FlightLeg_Flight_number, FlightLeg_LegNumber),
    FOREIGN KEY (FlightLeg_LegNumber, FlightLeg_Flight_number) REFERENCES Flight_Leg(LegNumber, Flight_number),
    FOREIGN KEY (Airplane_Id) REFERENCES Airplane(Airplane_Id),
    CHECK (Dept_Airport != Arr_Airport),
    CHECK (Arr_Scheduled_Time > Dept_Scheduled_Time)
);

Create Table rsv_voos.Seat(
    Seat_No int Identity(1,1),
    Leg_Instance_FlightLeg_Flight_number int NOT NULL, 
    Leg_Instance_FlightLeg_LegNumber int NOT NULL, 
    Leg_Instance_Date DATE NOT NULL, 
    Reservation_Customer_Name varchar(255),
    Reservation_Cphone varchar(13),
    PRIMARY KEY (Seat_No, Leg_Instance_FlightLeg_Flight_number, Leg_Instance_FlightLeg_LegNumber, Leg_Instance_Date),
    FOREIGN KEY (Leg_Instance_Date, Leg_Instance_FlightLeg_Flight_number, Leg_Instance_FlightLeg_LegNumber) REFERENCES Leg_Instance([Date], FlightLeg_Flight_number, FlightLeg_LegNumber)
);