package com.company.Piesa;

import com.company.Piesa.Piese.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tabla {
    public static int max_SIZE = 8;
    public Piesa[][] table;
    private List<Piesa> whitePiece;
    private List<Piesa> blackPiece;

    public Tabla() {
        this.table = new Piesa[max_SIZE][max_SIZE];
    }

    public boolean pozitionIsEmpty(Point p) {
        return table[p.getX()][p.getY()] == null;
    }

    public void print() {
        for (int i = 0; i < Tabla.max_SIZE; i++) {
            for (int j = 0; j < Tabla.max_SIZE; j++) {
                if (this.table[i][j] != null)
                    System.out.print(this.table[i][j].getId() + "\t");
                else
                    System.out.print(0 + "\t");
            }
            System.out.println();
        }
    }

    public void initGame() {
        whitePiece = new ArrayList<>();
        blackPiece = new ArrayList<>();

        for (int i = 0; i < Tabla.max_SIZE; i++) {
            Pion pw = new Pion(new Point(Tabla.max_SIZE - 2, i), 1);
            Pion pb = new Pion(new Point(1, i), -1);
            whitePiece.add(pw);
            blackPiece.add(pb);
        }

        Tura turaB1 = new Tura(new Point(0, 0), -1);
        Tura turaB2 = new Tura(new Point(0, Tabla.max_SIZE - 1), -1);

        blackPiece.add(turaB1);
        blackPiece.add(turaB2);

        Cal calB1 = new Cal(new Point(0, 1), -1);
        Cal calB2 = new Cal(new Point(0, Tabla.max_SIZE - 2), -1);
        blackPiece.add(calB1);
        blackPiece.add(calB2);

        Nebun nebunB1 = new Nebun(new Point(0, 2), -1);
        Nebun nebunB2 = new Nebun(new Point(0, Tabla.max_SIZE - 3), -1);
        blackPiece.add(nebunB1);
        blackPiece.add(nebunB2);

        Regina reginaB1 = new Regina(new Point(0, 3), -1);
        blackPiece.add(reginaB1);

        Rege regeB1 = new Rege(new Point(0, 4), -1);
        blackPiece.add(regeB1);

        // white
        Tura turaW1 = new Tura(new Point(Tabla.max_SIZE - 1, 0), 1);
        Tura turaW2 = new Tura(new Point(Tabla.max_SIZE - 1, Tabla.max_SIZE - 1), 1);
        whitePiece.add(turaW1);
        whitePiece.add(turaW2);

        Cal calW1 = new Cal(new Point(Tabla.max_SIZE - 1, 1), 1);
        Cal calW2 = new Cal(new Point(Tabla.max_SIZE - 1, Tabla.max_SIZE - 2), 1);
        whitePiece.add(calW1);
        whitePiece.add(calW2);

        Nebun nebunW1 = new Nebun(new Point(Tabla.max_SIZE - 1, 2), 1);
        Nebun nebunW2 = new Nebun(new Point(Tabla.max_SIZE - 1, Tabla.max_SIZE - 3), 1);
        whitePiece.add(nebunW1);
        whitePiece.add(nebunW2);

        Regina reginaW1 = new Regina(new Point(Tabla.max_SIZE - 1, 3), 1);
        whitePiece.add(reginaW1);

        Rege regeW1 = new Rege(new Point(Tabla.max_SIZE - 1, 4), 1);
        whitePiece.add(regeW1);


        for (var p : whitePiece) {
            table[p.getPozitiePeTabla().getX()][p.getPozitiePeTabla().getY()] = p;
        }

        for (var p : blackPiece) {
            table[p.getPozitiePeTabla().getX()][p.getPozitiePeTabla().getY()] = p;
        }

        print();
    }


    public void movePiesa(int x, int y) {
        print();
        if( table[x][y] == null) {
            return;
        }
        Piesa piesa = table[x][y];
        List<Point> points = piesa.getValidMovmentPozition(this);
        for (var p : points) {
            table[p.getX()][p.getY()] = new Pion(new Point(p.getX(), p.getY()), 11);
        }
        print();

        System.out.println(points.size());
        System.out.println(points);

        Scanner myObj = new Scanner(System.in);
        int poz = myObj.nextInt();
        Point toMoveTo = points.get(poz);
        piesa.setPozitiePeTabla(toMoveTo);

        table[toMoveTo.getX()][toMoveTo.getY()] = table[x][y];
        table[x][y] = null;

        print();
    }

}
