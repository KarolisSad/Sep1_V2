package model;

import java.util.ArrayList;

public class SessionList
{
  private ArrayList<Session> sessionList;

  public SessionList()
  {
    this.sessionList = new ArrayList<>();
  }

  public void addSession(Session session)
  {
    sessionList.add(session);
  }

  public void removeSessionByIndex(int index)
  {
    sessionList.remove(index);
  }

  public void removeSession(Session session)
  {
    if (sessionList.contains(session))
    {
      sessionList.remove(session);
    }
    else
    {
      throw new IllegalArgumentException("Session not in list.");
    }
  }

  public int getNumberOfSessions()
  {
    return sessionList.size();
  }

  public Session getSession(int index)
  {
    if (sessionList.size() >= index + 1)
    {
      return sessionList.get(index);
    }
    else
    {
      throw new ArrayIndexOutOfBoundsException(
          "Index out of bounds for SessionList. Entered index: " + index
              + ", highest possible index: " + (getNumberOfSessions() - 1));
    }
  }

  public boolean contains(Session session)
  {
    return sessionList.contains(session);
  }

  public String toString()
  {
    String s = "Sessions: ";
    for (int i = 0; i < sessionList.size(); i++)
    {
      s += "\n" + sessionList.get(i);
    }
    return s;
  }
}
