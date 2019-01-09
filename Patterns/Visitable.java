package ca.utoronto.utm.paint;

/**
 * Interface that allows an object to be visited by a visitor
 *
 */
public interface Visitable{
  public void accept(Visitor visitor);
}
