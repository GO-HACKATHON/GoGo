
# MIGI (右)

> Your clever transjogja assistant

<img src="migilogo.png" alt="Migi" width="325" height="325"/>

## Introduction

3,594 million peoples live in Yogyakarta and around 4.18 million tourists come to Yogyakarta every year. They need a fast and reliable transportation. There are so many solutions provided but they also create a new big problem *TRAFIC JAM*. Instead of creating a new solution, why don't we upgrade the existing one?

Introducing Migi, a wonderful application that help you solve this problem of transportation in Yogyakarta by improving performance of Transjogja. This smart and clever bot will help you to know the arrival time of Transjogja bus, providing information about the availability of the passenger seat, nearest bus stop locations and alternative routes to destinations. So your time will be saved efficiently.

## Get Started with Migi
### Installation

	You can install Migi by building the source code using IDE such as android studio or intelliJ and install it in your android device

### Running Migi

	You can run the app directly from your android device. For the server you can use this command in migi-server folder
	
	```
	$ mvn clean install
	$ mvn spring-boot:run
	```
	
## How Migi works

```
                                                        +----------+
                                               +-------->          |
+------------+             +----------------+  | +------+  API.AI  |
|            |             |                +--+ |      |          |
|            +------------->                <----+      +----------+
|    MIGI    |             |     server     |
|            <-------------+                <----+
|            |             |                +--+ |      +----------+
+------------+             +----------------+  | |      |          |
                                               | +------+  Gmaps   |
                                               +-------->          |
                                                        +----------+

```

## Features 
	Migi will give you information about
   * The arrival time of Transjogja bus
   * Availability of the passenger seat
   * Nearest bus stop locations
   * Alternative routes to destinations
## Support
If you have any problem dont hestitate to contact us at gogomigichan@gmail.com