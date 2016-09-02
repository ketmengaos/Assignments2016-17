#include <iostream>
using namespace std;

void selection_sort(int *low, int *high) 
{
	int *pnt1 = low; //Used to maintain pointer location for the cout statement.
	int *pnt2 = low; //Used to maintain pointer location for the for-loop statements.
	int count = high-low; //Makes 'count' the number of variables.

	for (int i = 0; i <= count; i++, low++) //Counters used to properly count the number of times we need to iterate. 
	{
		pnt2 = low + 1;
		for (int j = i+1; j <= count ; j++, pnt2++) 
			if (*low > *pnt2)
				swap(*low, *pnt2);
	}
	for (int k = 0; k <= count; k++, pnt1++)
		cout << *pnt1 << endl;
	
}

int* binary_search(int *low, int *high, int val)
{
    while (*high >= *low)
       {
       int *median = ((high-low) / 2 ) + low;
       cout << "Median: " << *median << " High: " << *high << " Low: : " << *low << endl;
       if (*median == val)
	       return median;
       else if (val > *median)
	       low = median + 1;
       else if (val < *median)
	       high = median - 1;
       }	   
    cout << "ending reached" << endl;
    return 0;	
}

int main()
{
	int amount;

	cout << "Enter the amount of numbers you wish to sort: " << endl;
	cin >> amount;
	cout << endl <<  "Enter numbers to sort: (enter null to stop) " << endl;
	
	int numbers[amount];	

	for (int i = 0; i < amount ;i++)
	{
		cin >> numbers[i];
	}

	cout << endl;

	int *p = &numbers[0];

	int size = sizeof(numbers)/sizeof(numbers[0]);
	selection_sort(&numbers[0], &numbers[size-1]);
	cout << endl;
	cout << "Does value exist in memory: (returns 0 if null) " << binary_search(&numbers[0], &numbers[size-1], 2) << endl;
}
