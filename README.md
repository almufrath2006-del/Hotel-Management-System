# Hotel Management System

## Overview
A console-based Hotel Management System developed using Core Java 
with JDBC and MySQL database integration. This project simulates 
real-world hotel operations where staff can manage customer bookings, 
track room occupancy, handle checkouts with automated billing, and 
maintain a wallet for revenue tracking — all backed by a MySQL database.

## Features
- Add customers with full details — name, phone, email, dates, and rooms
- Four room categories with fixed room number ranges
  (Simple: 1-5, A/C: 6-10, Deluxe: 11-15, Super Deluxe: 16-20)
- Room availability validation before booking
- Duplicate room number detection during booking
- Automated billing based on room type, count, and days of stay
- Customer checkout with two modes — Paid and Removed
- Wallet management — revenue auto-updated on customer checkout
- Search customer by name or room number
- View all customers with total customer count
- Real-time occupied rooms tracking using Java Collections

## Technologies Used
- Core Java
- JDBC — Java Database Connectivity
- MySQL Database
- Java Collections — LinkedHashSet, HashSet

## Database Tables
- customer — stores all booking and customer details
- wallet — tracks hotel revenue

## How to Run
1. Clone the repository
2. Create MySQL database named 'rooms'
3. Create customer and wallet tables
4. Add MySQL connector JAR to lib folder
5. Update DB credentials in code
6. Compile and run Hotel.java
