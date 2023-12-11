package com.datastructure;


import main.java.com.datastructure.FloorPlanning;
import main.java.com.datastructure.Position;

public class App 
{
    public static void main( String[] args )
    {
       FloorPlanning<Character> floor = new FloorPlanning<>();
		Position<Character> e = floor.decomposeHorizontal(floor.root(), 100, 'E');
		Position<Character> f = floor.decomposeVertical(e, 40, 'F');
		Position<Character> b = floor.decomposeVertical(floor.sibling(e), 20, 'B');
		Position<Character> c = floor.decomposeHorizontal(b, 35, 'C');
		Position<Character> d = floor.decomposeVertical(c, 70, 'D');
		System.out.println(floor);
		System.out.println("the height of root A-E (-)");
		System.out.println(floor.getHeight(floor.root()));
		System.out.println("the width of root A-E (-)");
		System.out.println(floor.getWidth(floor.root()));
		System.out.println();
		System.out.println("the height of A");
		System.out.println(floor.getHeight(floor.sibling(b)));
		System.out.println("the width of A");
		System.out.println(floor.getWidth(floor.sibling(b)));
		System.out.println();
		System.out.println("the height of E");
		System.out.println(floor.getHeight(floor.sibling(f)));
		System.out.println("the width of E");
		System.out.println(floor.getWidth(floor.sibling(f)));
		System.out.println();
		System.out.println("the height of F");
		System.out.println(floor.getHeight(f));
		System.out.println("the width of F");
		System.out.println(floor.getWidth(f));
		System.out.println();
		System.out.println("the height of B");
		System.out.println(floor.getHeight(floor.sibling(c)));
		System.out.println("the width of B");
		System.out.println(floor.getWidth(floor.sibling(c)));
		System.out.println();
		System.out.println("the height of C");
		System.out.println(floor.getHeight(floor.sibling(d)));
		System.out.println("the width of C");
		System.out.println(floor.getWidth(floor.sibling(d)));
		System.out.println();
		System.out.println("the height of D");
		System.out.println(floor.getHeight(d));
		System.out.println("the width of D");
		System.out.println(floor.getWidth(d));
		System.out.println();
		System.out.println("the height of A-B (|)");
		System.out.println(floor.getHeight(floor.sibling(e)));
		System.out.println("the width of A-B (|)");
		System.out.println(floor.getWidth(floor.sibling(e)));
		System.out.println();
		System.out.println("the height of F-E (|)");
		System.out.println(floor.getHeight(e));
		System.out.println("the width of F-E (|)");
		System.out.println(floor.getWidth(e));
		System.out.println();
		System.out.println("the height of B-C (-)");
		System.out.println(floor.getHeight(b));
		System.out.println("the width of B-C (-)");
		System.out.println(floor.getWidth(b));
		System.out.println();
		System.out.println("the height of C-D(|)");
		System.out.println(floor.getHeight(c));
		System.out.println("the width of C-D (|)");
		System.out.println(floor.getWidth(c));
    }
}
