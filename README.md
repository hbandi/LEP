*********** This is under development,we may change design or flows based on our future roadmap *********************

                                  Lightning Event Processor
LEP means Lightning event processor, which is nothing but complex event processing engine.LEP is basically designed to process millions of events and do farming with those events like counting/aggregation/reduction/merging/processing events on real-time basis.
While designing such complex systems, the one should use better patterns to maintain scalability, availability and accuracy.
Based on our experiences with CEP engines we want to design it simple.
Below all are separate services can be used with no complete system dependency.

![Screenshot](image.png)

1)	Filter
Every time when your system may receive millions of events, but you are not bother about all those events, you need only few events which your campaigns or use cases are configured or running. So you need to filter those events.
How to do that, we can do with a registered query with that event type. And more over every CEP engine has its own query language to filter those events.

We are designing our CEP not based on our own query language, we are going to design this component with SPEL (spring expression language) for every usecase Type,we associate one or more spel expressions. For every these particular type of events we apply registered spels against real-time events.

Example Spel (1.0):
((useCaseType matches 'REAL|OFFLINE') AND (customer Region matches 'IN|US') 
AND (order Amount gt '10000') AND (productBuyTime between ('01-09-2017:22:01:00','02-09-2017:22:01:00')  
AND (! ({11L, 12L}.contains All (itemIds))))

For every customer who buys items from India/USA and order amount should >10000 and transaction time between some range and non-applicable items for this offer.
	Customers who satisfy this criteria, we filter them.

2)	Managing Contexts
After filtering events we have to store or associate them to particular context for future usage, aggregations or calculations. This needs to be maintain in-memory not in the data bases.But these window configurations we could maintain in DB, since our instances are not always up and running.

Example (Refer Spel from the 1.0)
 In the above useCase mentioned in the 1.0, when customer meet that in ‘5’ times he has to get the 10% cashback on all the purchased amount.
To satisfy this case, we need to create a aggregation Context for this window.
WinterAgregationWindow (customer Name, currentNoOfTimes, expectedNoOfTimes, accumulatedPurchaseAmount)
When every player meets the 1.0 query we will populate this window and when it reaches the expected count, we will do the call back or initiate cashback.

Currently we are creating few predefined window templets for the usage, any one create customized windows.
Predefined Windows examples:
1)	EventAgregationCountWindow (event count should be 5 times and between time ranges )
2)	EventAgregationBasedOnFields (in the event purchase amount and Time Ranges and no Discount)
Not only to have these we need to join between the windows, and do the aggregations or min, max etc.
Suppose we have two windows like WinterAgregationWindow and PurchaseMethodAgregationWindow, in need give special cashback when customer meet the above two windows.
4) Pattern Windows
	This is some kind of event correlation, this kind of windows does not depend on single event it depends on series of events.
A Marketing guy want to create series of campaigns like (Camp1 cashback depends on camp2 and capm2 depends on camp3...etc.).
In this case we should listen series of dependent events to award cashback.
Basic pattern examples like 
i)	B event followed by A.
ii)	C followed by A,B
iii)	Not at all A,B,C
iv)	Series of 10 events.
5)  Restrictions Evaluation
	Sometimes you need to evaluate restriction based on some trigger.
Suppose in your website have a special page that will show items based on customer properties. Like based on gender or location or previous purchase history or age.
For this you can’t go to DB and execute queries then display items on the page, to make it simple based on customer object we will run through set of configured campaigns (spels) and get the matched use cases.









 





