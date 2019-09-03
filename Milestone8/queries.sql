---------------------------------------------------------------------------------------
-- QUERY TO KEEP TRACK OF ALL ROOM INFORMATION
---------------------------------------------------------------------------------------
SELECT 
    R.RoomNum,
    R.Floor,
    R.OccupancyLimit,
    RT.RoomTypeDescription,
    A.AmenityDescription
FROM
    HotelReservationSystem.ROOMS R
        JOIN
    HotelReservationSystem.ROOM_TYPES RT ON R.RoomTypeID = RT.RoomTypeID
        JOIN
    HotelReservationSystem.ROOM_AMENITIES RA ON R.RoomNum = RA.RoomNum
        JOIN
    AMENITIES A ON RA.AmenityID = A.AmenityID;

---------------------------------------------------------------------------------------
-- QUERY TO KEEP TRACK OF ALL RESERVATION INFORMATION
---------------------------------------------------------------------------------------
SELECT 
    GRR.ReservationID,
    C.CustomerName,
    C.CustomerPhone,
    C.CustomerEmail,
    G.GuestName,
    G.GuestAge,
    GRR.RoomNum
FROM
    HotelReservationSystem.GUEST_ROOM_RESERVATIONS GRR
        JOIN
    HotelReservationSystem.GUESTS G ON GRR.GuestID = G.GuestID
        JOIN
    CUSTOMERS C ON G.CustomerID = C.CustomerID;

---------------------------------------------------------------------------------------
--  QUERY TO KEEP TRACK OF ROOM RATES
---------------------------------------------------------------------------------------
SELECT 
    R.RoomNum,
    R.Floor,
    R.OccupancyLimit,
    RT.RoomTypeDescription,
    RR.StartDate,
    RR.EndDate,
    RR.ROOMRATE
FROM
    HotelReservationSystem.ROOMS R
        JOIN
    HotelReservationSystem.ROOM_TYPES RT ON R.RoomTypeID = RT.RoomTypeID
        JOIN
    HotelReservationSystem.ROOM_RATES RR ON RR.RoomTypeID = RT.RoomTypeID;

---------------------------------------------------------------------------------------
-- QUERY TO KEEP TRACK OF ADDONS
---------------------------------------------------------------------------------------
SELECT 
    AOR.ID,
    AO.AddOnDescription,
    AOR.StartDate,
    AOR.EndDate,
    AOR.AddOnRate
FROM
    HotelReservationSystem.ADDON_RATES AOR
        JOIN
    HotelReservationSystem.ADDONS AO ON AOR.ADDONID = AO.ADDONID;

---------------------------------------------------------------------------------------
-- QUERIES TO KEEP TRACK OF BILL HEADERS AND BILL DETAILS
---------------------------------------------------------------------------------------
SELECT 
    *
FROM
    HotelReservationSystem.BILL_SUMMARY;
SELECT 
    *
FROM
    HotelReservationSystem.BILL_DETAILS;

---------------------------------------------------------------------------------------
-- QUERIES TO KEEP TRACK OF PROMOS
---------------------------------------------------------------------------------------
SELECT 
    *
FROM
    HotelReservationSystem.PROMOS;