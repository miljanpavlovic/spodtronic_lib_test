package com.example.miljan.playertest;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Miljan on 6/23/2015.
 */
public class History {
    private static final int VERSION = 1;
    private static final String FILENAME = "history.dat";

    private Context ctx;
    private ArrayList<String> list;


    public History( Context ctx ) {
        this.ctx = ctx;
    }


    public ArrayAdapter<String> getArrayAdapter() {
        return new ArrayAdapter<String>( ctx, android.R.layout.simple_dropdown_item_1line, list );
    }


    public void addUrl( String url ) {
        if (!list.contains( url )) list.add( url );
    }


    public int size() {
        return list.size();
    }


    public void read() {
        list = new ArrayList<String>();

        try {
            DataInputStream dis = new DataInputStream( ctx.openFileInput( FILENAME ));

            dis.readInt(); // VERSION
            int n = dis.readInt();

            while (n-- > 0) list.add( dis.readUTF());
        }
        catch (IOException e) {}
    }


    public void write() {
        try {
            DataOutputStream dos = new DataOutputStream( ctx.openFileOutput( FILENAME, 0 ));

            dos.writeInt( VERSION );
            dos.writeInt( list.size());

            for (String url : list) dos.writeUTF( url );
        }
        catch (IOException e) {}
    }

}
