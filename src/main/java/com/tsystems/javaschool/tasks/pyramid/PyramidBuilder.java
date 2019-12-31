package com.tsystems.javaschool.tasks.pyramid;

import java.util.List;

public class PyramidBuilder 
{

    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */


    public int[][] buildPyramid(List<Integer> inputNumbers)
    {
        if (inputNumbers.size() == Integer.MAX_VALUE-1)
            throw new CannotBuildPyramidException();
        try
        {
            int count = inputNumbers.size();

            MySort(inputNumbers);
//            Collections.sort(inputNumbers);
            int i = 2;
            int[] arr = new int[count];
            arr[0] = 1;
            arr[1] = 1;
            int[] numOfCol = new int[count];
            numOfCol[0] = 1;
            numOfCol[1] = 1;
            while (arr[i - 1] < count)
            {
                arr[i] = arr[i - 1] + i;
                numOfCol[i] = numOfCol[i - 1] + 2;
                i++;
            }

            int numOfRows = 0;
            for (int j = 0; j < arr.length; j++)
            {
                if(arr[j] == count)
                {
                    numOfRows = j;
                    break;
                }

                if (j == arr.length - 1)
                    throw new CannotBuildPyramidException();
            }

            int[][] arrr = new int[numOfRows][numOfCol[numOfRows]];

            i = 0;
            for(int k = 0; k < numOfRows; k++)
            {
                for(int v = 0; v < ((numOfCol[numOfRows] - 1)/2) - k; v++)
                {
                    arrr[k][v] = 0;
                }
                for(int u = ((numOfCol[numOfRows] - 1)/2) - k; u <= ((numOfCol[numOfRows] - 1)/2) + k; u += 2)
                {
                    arrr[k][u] = inputNumbers.get(i);
                    i++;
                }
                for(int z = ((numOfCol[numOfRows] - 1)/2) - k + 1; z <= ((numOfCol[numOfRows] - 1)/2) + k - 1; z += 2)
                {
                    arrr[k][z] = 0;
                }
                for(int x = ((numOfCol[numOfRows] - 1)/2) + k + 1; x < numOfCol[numOfRows]; x++)
                {
                    arrr[k][x] = 0;
                }

            }
            return arrr;

        }
        catch (Exception ex){

            throw new CannotBuildPyramidException();
        }

    }

    public void MySort(List<Integer> param)
	{
        int myIndex;
        int myInt = -1;

        for(int i = 0; i < param.size() - 1; i++)
        {
            myIndex = -1;
            myInt = param.get(i);
            for(int j = i + 1; j < param.size(); j++)
            {
                if(param.get(j) < myInt)
                {
                    myInt = param.get(j);
                    myIndex = j;
                }
            }
            if(myIndex != -1)
            {
                myInt = param.get(myIndex);
                param.set(myIndex, param.get(i));
                param.set(i, myInt);
            }
        }
    }

}