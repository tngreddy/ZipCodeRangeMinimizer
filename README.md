# ZipCodeRangeMinimizer
Application which minimizes the arbitary/overlapping list of zipcode ranges

# Description

Sometimes items cannot be shipped to certain zip codes, and the rules for these restrictions are stored as a series of ranges of 5 digit codes. For example if the ranges are:
[94133,94133] [94200,94299] [94600,94699]
 
Then the item can be shipped to zip code 94199, 94300, and 65532, but cannot be shipped to 94133, 94650, 94230, 94600, or 94299.

Any item might be restricted based on multiple sets of these ranges obtained from multiple sources.


REQUIREMENT

Given a collection of 5-digit ZIP code ranges (each range includes both their upper and lower bounds), provide an algorithm that produces the minimum number of ranges required to represent the same restrictions as the input.

NOTES

- The ranges above are just examples, your implementation should work for any set of arbitrary ranges
- Ranges may be provided in arbitrary order
- Ranges may or may not overlap
- Your solution will be evaluated on the correctness and the approach taken, and adherence to coding standards and best practices
 
EXAMPLES:

If the input = [94133,94133] [94200,94299] [94600,94699]
Then the output should be = [94133,94133] [94200,94299] [94600,94699]

If the input = [94133,94133] [94200,94299] [94226,94399]
Then the output should be = [94133,94133] [94200,94399]

# Approach
- Take the input as the list of zipCode ranges(array)
- Validate the Input
- Sort the list based on the lowerbound of each zipcode range
- Iterate and compare the upperBound of the current range with the lowerbound of the next range and merge if necessary.

# Application

 This is a spring-boot application, which provides a REST endpoint to take the request and provides the list of minimized zipcode ranges

 Language : Java
 
 Build Tool : Maven

# Steps to deploy

 BUILD
 
  `mvn clean install`
 
 RUN
 
 `mvn spring-boot:run`
 
 Server starts on default port 8080, access the endpoint as follows
```json
 EndPoint : http://localhost:8080/zipcode
 Method : POST
 Request :
 {
  "zipCodeRangeList":[
    [94133,94133],
    [94200,94299],
    [94226,94399]
    ]
 }
 Response :
 {
  "zipCodeRangeList":[
    [94133,94133],
    [94200,94399]
    ]
 }
```
 

