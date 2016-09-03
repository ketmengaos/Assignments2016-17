//*******************************************************************
//****|| Ket-Meng Jimmy Cheng - September 6, 2016 - Program #1 ||****
//****|| ----------------------------------------------------- ||**** 
//****|| This programs reads from standard input, allocates -- ||****
//****|| space on using an array, then inserts the numbers --- ||****
//****|| defined by the user. It then sorts via selection_sort ||****
//****|| using solely pointers, then asks the user to input a  ||****
//****|| a value to be found for binary_search. If it exists,  ||****
//****|| binary_search returns an address--if not, it returns  ||****
//****|| zero as an integer.                                   ||****
//*******************************************************************

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
       if (*median == val)
	       return median;
       else if (val > *median)
	       low = median + 1;
       else if (val < *median)
	       high = median - 1;
       }	   
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

	int size = sizeof(numbers)/sizeof(numbers[0]);
	selection_sort(&numbers[0], &numbers[size-1]); //Selection_sort method.
	cout << endl << "Enter an integer to find: ";
	cin >> amount;
	cout << "Does value exist in memory (returns 0 if it does not exist): " << binary_search(&numbers[0], &numbers[size-1], amount) << endl; //Tests binary_sort method.
}
