#include <iostream>
using namespace std;

void selection_sort(int *low, int *high) 
{
	int *pnt1 = low; //Used to maintain pointer location for the cout statement.
	int *pnt2 = low; //Used to maintain pointer location for the for-loop statements.
	
	int count = high-low;

	for (int i = 0; i < count; i++, low++) //Counters used to properly count the number of times we need to iterate. Could not use *low != *high in the end since *high obviously changes.
	{
		pnt2 = low + 1;
		for (int j = i+1; j < count ; j++, pnt2++) 
		{
			if (*low > *pnt2)
				swap(*low, *pnt2);
		}
	}
	for (int k = 0; k < count; k++, pnt1++)
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
	/*
	int first = 24;
	int second = 49;
	int third = 31;
	int fourth = 22;
	int fifth = 21;
	int sixth = 24;
	int seventh = 99;
	int eighth = 1;
	int ninth = 3;
	int tenth = 29;
	*/

	int numbers[] = {24, 49, 31, 22, 21, 24, 99, 1, 3, 29};
	int *p = &numbers[0];

	int size = sizeof(numbers)/sizeof(numbers[0]);
	//cout << "p12: " << *(p+19) << endl;
	
	selection_sort(&numbers[0], &numbers[size-1]);
	cout << endl;
	cout << "Does value exist in memory: " << binary_search(&numbers[0], &numbers[size-1], 24) << endl;
}
