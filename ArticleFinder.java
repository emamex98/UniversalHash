/*
  ______ __  __          __  __ ________   _____   ___
 |  ____|  \/  |   /\   |  \/  |  ____\ \ / / _ \ / _ \
 | |__  | \  / |  /  \  | \  / | |__   \ V / (_) | (_) |
 |  __| | |\/| | / /\ \ | |\/| |  __|   > < \__, |> _ <
 | |____| |  | |/ ____ \| |  | | |____ / . \  / /| (_) |
 |______|_|  |_/_/    \_\_|  |_|______/_/ \_\/_/  \___/

Emanuel Estrada Larios - A01633605
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

class ArticleFinder extends JPanel implements ActionListener{

  private UniversalHashing finder;
  private JTextField tfNombreArchivo;
  private JButton btAdd, btSearch, btDlt, btExport, btExaminar;
  private File articulo;

  ///////////////////////////////////////////////////////////

  public ArticleFinder(){
    super();
    this.setPreferredSize(new Dimension(200,300));
    this.finder = new UniversalHashing();

    this.add(new JLabel("<html><center><br><b>Ruleta 5<br>Emanuel Estrada Larios<br>Universal Hash</b><br><br>Problema: Los editores de TecReview quieren<br>implemetar un programa sencillo para guardar sus articulos.<br>Este herramienta permite que se cargue un archivo con el articulo<br>y este se guardara en una tabla hash universal.<br><br>Seleccionar un archivo:<br><br></center></html>"));

    this.tfNombreArchivo = new JTextField(30);
    this.add(this.tfNombreArchivo);

    this.btExaminar = new JButton("Examinar...");
    this.btExaminar.addActionListener(this);
    this.add(this.btExaminar);

    this.btAdd = new JButton("Guardar");
    this.btAdd.addActionListener(this);
    this.add(this.btAdd);

    this.btSearch = new JButton("Buscar");
    this.btSearch.addActionListener(this);
    this.add(this.btSearch);

    this.btDlt = new JButton("Eliminar");
    this.btDlt.addActionListener(this);
    this.add(this.btDlt);

    this.btExport = new JButton("Descargar lista de registros");
    this.btExport.addActionListener(this);
    this.add(this.btExport);

    // this.btDonate = new JButton("Dona ahora a la Cruz Roja Mexicana");
    // this.btDonate.setForeground(Color.RED);
    // this.btDonate.addActionListener(this);
    // this.add(this.btDonate);

  }

  //////////////////////////////////////////////////////////

  public void actionPerformed(ActionEvent e){
    if(e.getSource() == this.btExaminar){

      JFileChooser chooser = new JFileChooser();
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
          "Archivos de Texto", "txt");
      chooser.setFileFilter(filter);
      int returnVal = chooser.showOpenDialog(this);

      if(returnVal == JFileChooser.APPROVE_OPTION) {
        this.articulo = chooser.getSelectedFile();
        this.tfNombreArchivo.setText(this.articulo.getName());
      }

      this.revalidate();
    }
    else if(e.getSource() == this.btAdd){
      int j = this.calculateKey();
      j = this.finder.insertElement(new Key(j, this.articulo));
      JOptionPane.showMessageDialog(null,"Articulo gaurdado en indice " + j);
    }

    else if(e.getSource() == this.btSearch){
      int j = this.calculateKey();
      j = this.finder.searchElement(new Key(j, this.articulo));
      if(j != -1)
        JOptionPane.showMessageDialog(null,"Articulo encontrado en indice " + j);
      else
        JOptionPane.showMessageDialog(null,"Articulo no encontrado");
    }

    else if(e.getSource() == this.btDlt){
      int j = this.calculateKey();
      this.finder.deleteElement(new Key(j, this.articulo));
    }

  }

  ///////////////////////////////////////////////////////////

  private int calculateKey(){

    char[] charArr = this.articulo.getName().toCharArray();
    int asciiKey = 0;

    for(int i=0; i<charArr.length; i++)
      asciiKey += (int) charArr[i];

    return asciiKey;
  }

  // //////////////////////////////////////////////////////////
  //
  // public void addPerson(String name){
  //   char[] ch = name.toCharArray();
  //   for(int i=0; i<ch.length; i++){
  //     if (ch[i] < 32 || (ch[i] > 90 && ch[i] < 97) || ch[i] > 122) {
  //       JOptionPane.showMessageDialog(null,"Porfavor ingrese caracteres validos.");
  //       return;
  //     }
  //   }
  //   Key newPerson = new Key(calculateKey(name),name);
  //   finder.insertElement(newPerson);
  //   JOptionPane.showMessageDialog(null,name + " ha sido registrado(a) como seguro(a).");
  // }
  //
  // //////////////////////////////////////////////////////////
  //
  // public String searchPerson(String name){
  //   int j = finder.searchElement(new Key(calculateKey(name),name));
  //   if(j!=0)
  //     return "Registro #" + j + ": " + (finder.getIndex(j)).data.toString() + " ha sido reportado(a) como seguro(a).";
  //   else
  //     return "No existe registro de la persona que buscas.";
  // }
  //
  // /////////////////////////////////////////////////////////
  //
  // public void deletePerson(String name){
  //   if(searchPerson(name) != "No existe registro de la persona que buscas."){
  //     finder.deleteElement(new Key(calculateKey(name),name));
  //     JOptionPane.showMessageDialog(null,"El registro de " + name + " ha sido eleminado.");
  //   }
  //   else
  //     JOptionPane.showMessageDialog(null,"No existe registro de la persona que buscas.");
  //
  // }

  /////////////////////////////////////////////////////////

  public static void main(String[] args) {
    // ArticleFinder ps = new ArticleFinder();
    // ps.addPerson("Emanuel");
    // System.out.println(ps.searchPerson("Emanuel"));
    // ps.deletePerson("Emanuel");
    // System.out.println(ps.searchPerson("Emanuel"));
  }


}
