package com.tsystems.javaschool.tasks.subsequence;

import java.util.List;

public class Subsequence 
{

    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
    @SuppressWarnings("rawtypes")
    public boolean find(List x, List y) 
	{
        if(x == null || y == null)
            throw new IllegalArgumentException();

        if(x.size() == 0)
            return true;

        int z = 0;
        for(int i = 0; i < x.size(); i++)
        {
            for(int j = z; j < y.size(); j++)
            {
                if(x.get(i) == y.get(j))
                {
                    z = j;
                    if(i == x.size() - 1 && x.get(x.size() - 1) == y.get(j))
                    {
                        return true;
                    }
                    break;
                }
                if(j == y.size() - 1 && x.get(i) != y.get(y.size() - 1))
                {
                    return false;
                }
                if(i == x.size() - 1 && x.get(x.size() - 1) == y.get(j))
                {
                    return true;
                }
            }
        }
        return false;
    }
}
