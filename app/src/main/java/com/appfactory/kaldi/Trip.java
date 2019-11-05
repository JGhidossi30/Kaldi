package com.appfactory.kaldi;

public class Trip
{
    public Location startPoint, endPoint;

    /**
     *
     * @return
     */
    public Location getStartPoint()
    {
        return startPoint;
    }

    /**
     *
     * @param startPoint
     */
    public void setStartPoint(Location startPoint)
    {
        this.startPoint = startPoint;
    }

    /**
     * 
     * @return
     */
    public Location getEndPoint()
    {
        return endPoint;
    }

    /**
     *
     * @param endPoint
     */
    public void setEndPoint(Location endPoint)
    {
        this.endPoint = endPoint;
    }
}
