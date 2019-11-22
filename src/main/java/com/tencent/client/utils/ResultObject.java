package com.tencent.client.utils;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangsilun
 * Date: 12-10-20
 * Time: 下午4:14
 * To change this template use File | Settings | File Templates.
 */

public class ResultObject
{
    private int rsltCode = 0;// 结果代码
    private String rsltComment="";// 结果说明
    private Object rsltObject;// 结果对象，可以是对象集合，也可以是特定对象
    private String rsltExtend;// 扩展用
    private String rsltType;// 结果对象类型
    private String rsltComment1="";
    private int rsltCode1;
    private double rsltDoubleValue;
    private boolean rsltBooleanValue;
    private Date rsltDateValue;
    private int rsltIntValue;
    private String rsltStringValue;
    private HashMap paramsMap = new HashMap();
    public ResultObject()
    {
    }

    public static void main(String[] args)
    {
        ResultObject resultobject = new ResultObject();
    }

    public void setRsltCode(int rsltCode)
    {
        this.rsltCode = rsltCode;
    }

    public void setRsltComment(String rsltComment)
    {
        this.rsltComment = rsltComment;
    }

    public void setRsltObject(Object rsltObject)
    {
        this.rsltObject = rsltObject;
    }

    public void setRsltExtend(String rsltExtend)
    {
        this.rsltExtend = rsltExtend;
    }

    public void setRsltType(String rsltType)
    {
        this.rsltType = rsltType;
    }

    public void setRsltComment1(String rsltComment1) {
        this.rsltComment1 = rsltComment1;
    }

    public void setRsltCode1(int rsltCode1) {
        this.rsltCode1 = rsltCode1;
    }

    public void setRsltDoubleValue(double rsltDoubleValue) {
        this.rsltDoubleValue = rsltDoubleValue;
    }

    public void setRsltBooleanValue(boolean rsltBooleanValue) {
        this.rsltBooleanValue = rsltBooleanValue;
    }

    public void setRsltDateValue(Date rsltDateValue) {
        this.rsltDateValue = rsltDateValue;
    }

    public void setRsltIntValue(int rsltIntValue) {
        this.rsltIntValue = rsltIntValue;
    }

    public void setRsltStringValue(String rsltStringValue) {
        this.rsltStringValue = rsltStringValue;
    }

    public int getRsltCode()
    {
        return rsltCode;
    }

    public String getRsltComment()
    {
        return rsltComment;
    }

    public Object getRsltObject()
    {
        return rsltObject;
    }

    public String getRsltExtend()
    {
        return rsltExtend;
    }

    public String getRsltType()
    {
        return rsltType;
    }

    public String getRsltComment1() {
        return rsltComment1;
    }

    public int getRsltCode1() {
        return rsltCode1;
    }

    public double getRsltDoubleValue() {
        return rsltDoubleValue;
    }

    public boolean isRsltBooleanValue() {
        return rsltBooleanValue;
    }

    public Date getRsltDateValue() {
        return rsltDateValue;
    }

    public int getRsltIntValue() {
        return rsltIntValue;
    }

    public String getRsltStringValue() {
        return rsltStringValue;
    }
    public void addParameter(String paramName,Object paramValue) {
        paramsMap.put(paramName,paramValue);
    }
    public Object getParameter(String paramName) {
        if (paramsMap.containsKey(paramName)) {
            return paramsMap.get(paramName);
        } else {
            return null;
        }
    }
}
