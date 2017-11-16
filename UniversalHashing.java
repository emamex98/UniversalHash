/*
  ______ __  __          __  __ ________   _____   ___
 |  ____|  \/  |   /\   |  \/  |  ____\ \ / / _ \ / _ \
 | |__  | \  / |  /  \  | \  / | |__   \ V / (_) | (_) |
 |  __| | |\/| | / /\ \ | |\/| |  __|   > < \__, |> _ <
 | |____| |  | |/ ____ \| |  | | |____ / . \  / /| (_) |
 |______|_|  |_/_/    \_\_|  |_|______/_/ \_\/_/  \___/

Emanuel Estrada Larios - A01633605
*/

import java.util.Random;

public class UniversalHashing{

  private int h, u, m;
  private Random rHash;
  private Node[] hashTable;

  ///////////////////////////////////////////////////////

  private class Node{
    Node next;
    Key data;

    public Node(Key key, Node next){
      this.data = key;
      this.next = next;
    }

  }

  ///////////////////////////////////////////////////////

  public UniversalHashing(){
    /* Si tenemos un H funciones y U llaves, entonces la condicion
    de (H/m)/H = 1/m se cumple, y por lo tanto H es universal */
    this.h = 3;
    this.u = 5;

    this.m = this.u + 1;
    this.hashTable = new Node[this.m];
    this.rHash = new Random();
  }

  ///////////////////////////////////////////////////////

  private int hash(Key key){
    int randomInt = rHash.nextInt(this.h);
    return (key.k * randomInt) % this.m;
  }

  ///////////////////////////////////////////////////////

  public int insertElement(Key key){
    int j = hash(key);

    if(this.hashTable[j] == null){
      this.hashTable[j] = new Node(key, null);
      //System.out.println("Insertado en: " + j);
      return j;
    }
    else{
      Node next = this.hashTable[j];
      this.hashTable[j] = new Node(key, next);
      //System.out.println("Insertado en: " + j);
      return j;
    }

  }

  ///////////////////////////////////////////////////////

  public int searchElement(Key key){

    for(int j=0; j<this.hashTable.length; j++){
      if(this.hashTable[j] != null){
        if(this.hashTable[j].data.k == key.k){
          return j;
        }
        else{
          Node start = this.hashTable[j].next;
          while(start != null){
            if(start.data.k == key.k)
            return j;
            else
            start = start.next;
          }
        }
      }
    }

    return -1;
  }

  ///////////////////////////////////////////////////////

  public void deleteElement(Key key){
    int j = searchElement(key);

    if(j != -1){
      if(this.hashTable[j].data.k == key.k){
        if(this.hashTable[j].next != null){
          this.hashTable[j] = this.hashTable[j].next;
        }
        else{
          this.hashTable[j] = null;
        }
      }
      else{
        Node primero = this.hashTable[j];
        Node segundo = this.hashTable[j].next;
        boolean borrado = false;

        while(borrado == false){
          if(segundo.data.k == key.k && segundo.next == null){
            primero.next = null;
            borrado = true;
          }
          else if(segundo.data.k == key.k && segundo.next != null){
            primero.next = segundo.next;
            borrado = true;
          }
          else{
            primero = primero.next;
            segundo = segundo.next;
          }
        }
      }
    }

  }

  ///////////////////////////////////////////////////////

  public String[] printTable(){
    String[] returnString = new String[this.m];

    for (int i=0; i<this.m; i++) {

      if(this.hashTable[i] != null){
        Node start = this.hashTable[i];
        returnString[i] = Integer.toString(start.data.k);
        start = start.next;

        while(start != null){
          returnString[i] += ", " + start.data.k;
          start = start.next;
        }

        System.out.println(returnString[i]);
      }
    }

    return returnString;
  }

  ///////////////////////////////////////////////////////

  public static void main(String[] args) {
    UniversalHashing uh = new UniversalHashing();
    Key k = new Key(1024,"");
    uh.insertElement(new Key(1024,""));
    uh.insertElement(new Key(4039,""));
    uh.insertElement(new Key(9754,""));
    uh.insertElement(new Key(1024,""));
    uh.insertElement(new Key(3745,""));
    uh.printTable();
    // uh.deleteElement(new Key(1024,""));
    // uh.printTable();
  }


}
