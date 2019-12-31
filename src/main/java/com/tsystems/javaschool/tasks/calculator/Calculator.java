package com.tsystems.javaschool.tasks.calculator;

import java.util.*;



public class Calculator 
{



    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */


    public String evaluate(String statement)
    {
        try
        {
            String res = doParse(myRPN(statement));
            if ( res != "Infinity" )
            {
                if (res.endsWith(".0"))
                    res=res.substring(0,res.length()-2);
                return res;
            }
            else
                return null;
        } catch (Exception e)
        {
            return null;
        }
    }

    private static String myRPN(String patam) throws Exception
    {
        StringBuilder delimiter = new StringBuilder("");
        StringBuilder output = new StringBuilder("");

        char a;
        char b;

        for (int i = 0; i < patam.length(); i++)
        {
            a = patam.charAt(i);
            if (operator(a))
            {
                while (delimiter.length() > 0)
                {
                    b = delimiter.substring(delimiter.length()-1).charAt(0);
                    if (operator(b) && (priority(a) <= priority(b)))
                    {
                        output.append(" ").append(b).append(" ");
                        delimiter.setLength(delimiter.length()-1);
                    }
                    else
                        {
                        output.append(" ");
                        break;
                    }
                }
                output.append(" ");
                delimiter.append(a);
            }
            else if ('(' == a)
            {
                delimiter.append(a);
            }
            else if (')' == a)
            {
                b = delimiter.substring(delimiter.length()-1).charAt(0);
                while ('(' != b)
                {
                    if (delimiter.length() < 1)
                    {
                        throw new Exception();
                    }
                    output.append(" ").append(b);
                    delimiter.setLength(delimiter.length()-1);
                    b = delimiter.substring(delimiter.length()-1).charAt(0);
                }
                delimiter.setLength(delimiter.length()-1);
            }
            else
            {
                output.append(a);
            }
        }
        while (delimiter.length() > 0)
        {
            output.append(" ").append(delimiter.substring(delimiter.length()-1));
            delimiter.setLength(delimiter.length()-1);
        }

        return  output.toString();
    }

    private static boolean operator(char param)
    {
        switch (param)
        {
            case '-':
            case '+':
            case '*':
            case '/':
                return true;
        }
        return false;
    }

    private static byte priority(char param)
    {
        switch (param)
        {
            case '*':
            case '/':
                return 2;
        }
        return 1;
    }

    private static String doParse(String param) throws Exception
    {
        double a = 0;
        double b = 0;
        String s;

        Deque<Double> d = new ArrayDeque<Double>();
        StringTokenizer st = new StringTokenizer(param);

        while(st.hasMoreTokens())
        {
            try
            {
                s = st.nextToken().trim();
                if (1 == s.length() && operator(s.charAt(0)))
                {
                    if (d.size() < 2)
                    {
                        throw new Exception();
                    }
                    b = d.pop();
                    a = d.pop();
                    switch (s.charAt(0))
                    {
                        case '+':
                            a += b;
                            break;
                        case '-':
                            a -= b;
                            break;
                        case '/':
                            a /= b;
                            break;
                        case '*':
                            a *= b;
                            break;
                        default:
                            throw new Exception();
                    }
                    d.push(a);
                }
                else
                {
                    a = Double.parseDouble(s);
                    d.push(a);
                }
            }
            catch (Exception e)
            {
                throw new Exception();
            }
        }

        if (d.size() > 1)
        {
            throw new Exception();
        }
        return d.pop().toString();
    }

}