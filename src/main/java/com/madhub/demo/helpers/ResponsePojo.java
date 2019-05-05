/*
 * Class: ResonsePojo
 *
 * Created on May 4, 2019
 *
 * (c) Copyright Lam Research Corporation, unpublished work, created 2019
 * All use, disclosure, and/or reproduction of this material is prohibited
 * unless authorized in writing.  All Rights Reserved.
 * Rights in this program belong to:
 * Lam Research Corporation
 * 4000 N. First Street
 * San Jose, CA
 */
package com.madhub.demo.helpers;

public class ResponsePojo
{

    public int id;

    public String title;

    public String body;

    public int userId;

    /*    @Override
    public boolean equals(Object obj)
    {
    
        // checking if both the object references are
        // referring to the same object.
        if (this == obj)
        {
            return true;
        }
    
        // it checks if the argument is of the
        // type Geek by comparing the classes
        // of the passed argument and this object.
        // if(!(obj instanceof Geek)) return false; ---> avoid.
        if (obj == null || obj.getClass() != this.getClass())
        {
            return false;
        }
    
        // type casting of the argument.
        ResponsePojo pojoObj = (ResponsePojo) obj;
    
        // comparing the state of argument with
        // the state of 'this' Object.
        return (pojoObj.id == this.id && pojoObj.userId == this.userId && pojoObj.body.equals(this.body)
                && pojoObj.title.equals(this.title));
    }
    
    @Override
    public int hashCode()
    {
    
        // We are returning the Geek_id
        // as a hashcode value.
        // we can also return some
        // other calculated value or may
        // be memory address of the
        // Object on which it is invoked.
        // it depends on how you implement
        // hashCode() method.
        return this.id;
    }*/
}
