package sample;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.ResultSet;


public class Main extends Application {
    private static HBox myHBox()
    {
        HBox temp=new HBox(10);
        temp.setAlignment(Pos.CENTER);
        temp.setPadding(new Insets(20,20,20,20));
        return temp;
    }
    private static VBox myVBox()
    {
        VBox temp=new VBox(15);
        temp.setAlignment(Pos.CENTER);
        temp.setPadding(new Insets(15,15,15,15));
        return temp;
    }
    public static void setBackground(VBox layout,String url) throws FileNotFoundException {
        Image image = new Image(new FileInputStream(url));
        Background bk = new Background(new BackgroundImage(image,BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT , BackgroundPosition.DEFAULT, new BackgroundSize(3000, 1100, false, false, true, true)));
        layout.setBackground(bk);
    }
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Stage window=new Stage();
        window.setTitle("XYZ SCHOOL FOR CHILDREN");
        Button About=new Button("About Us");
        About.setFont(new Font("Cambria", 20));
        Button AboutSchool=new Button("About School");
        AboutSchool.setFont(new Font("Cambria", 20));
        Button Admission=new Button("Admission Process");
        Admission.setFont(new Font("Cambria", 20));
        Button ContactUs=new Button("Contact Us");
        ContactUs.setFont(new Font("Cambria", 20));
        Button VisionAndMission=new Button("Vision and Mission");
        VisionAndMission.setFont(new Font("Cambria", 20));
        About.setOnAction(event -> {
            try {
                display("About");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        AboutSchool.setOnAction(event -> {
            try {
                display("About School");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        Admission.setOnAction(event -> {
            try {
                display("Admission");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        ContactUs.setOnAction(event -> {
            try {
                display("Contact Us");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        VisionAndMission.setOnAction(event -> {
            try {
                display("Vision and Mission");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        HBox Static=myHBox();
        Static.getChildren().addAll(AboutSchool,Admission,ContactUs,VisionAndMission);
        About.setOnAction(event -> {
            try {
                test();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        Button FacultyLogin=new Button("Faculty Login");
        FacultyLogin.setFont(new Font("Cambria", 20));
        Button StudentLogin=new Button("Student Login");
        StudentLogin.setFont(new Font("Cambria", 20));
        StudentLogin.setOnAction(event -> {window.hide();
            try {
                AuthenticationWindow(window,"student");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            window.show();});
        FacultyLogin.setOnAction(event -> {window.hide();
            try {
                AuthenticationWindow(window,"faculty");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            window.show();});

        VBox layout=myVBox();

        setBackground(layout,"C:\\Users\\rajiv\\OneDrive\\Desktop\\mp_karan\\src\\sample\\images\\s3.jpg");

        window.setMaximized(true);

        layout.getChildren().addAll(Static,FacultyLogin,StudentLogin,About);

        Scene scene =new Scene(layout,300,300);
        window.setScene(scene);
        window.show();
    }

    public void AuthenticationWindow(Stage previous,String AuthType) throws FileNotFoundException {
        Stage window=new Stage();
        window.setMaximized(true);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(AuthType+" Login");
        Label warning=new Label("");
        Label Passwordlabel=new Label("Password:");
        Passwordlabel.setStyle("-fx-font-size:25");
        Label Usernamelabel=new Label("User Name:");
        Usernamelabel.setStyle("-fx-font-size:25");
        TextField Username=new TextField();
        PasswordField Password=new PasswordField();
        HBox PasswordBox=myHBox();
        HBox UsernameBox=myHBox();
        PasswordBox.getChildren().addAll(Passwordlabel,Password);
        UsernameBox.getChildren().addAll(Usernamelabel,Username);
        Button Login=new Button("Login");
        Login.setFont(new Font("Cambria", 20));
        Login.setOnAction(event -> {
            if(Username.getText().isEmpty()) {warning.setText("Enter UserName");}
            else if(Password.getText().isEmpty()) {warning.setText("Enter Password");}
            else if(AuthType.equals("faculty")&&mysql.Authenticate(Username.getText(),Password.getText(),"faculty"))
            {
                try {
                    FacultyLogin(Username.getText());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Username.setText("");
                Password.setText("");
            }
            else if(AuthType.equals("student") && Username.getText().equals(Password.getText()))
            {
                try {
                    StudentLogin(Username.getText());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Username.setText("");
                Password.setText("");
            }
            else {warning.setText("Invalid Credentials");}
        });
        VBox layout=myVBox();
        setBackground(layout,"C:\\Users\\rajiv\\OneDrive\\Desktop\\mp_karan\\src\\sample\\images\\login7.jpg");

        layout.getChildren().addAll(UsernameBox,PasswordBox);
        layout.getChildren().addAll(Login,warning);


        Scene scene= new Scene(layout,300,300);
        window.setScene(scene);
        window.showAndWait();
    }

    public static void test() throws FileNotFoundException {
        Stage window=new Stage();
        window.setMaximized(true);
        window.setTitle("About");

        Label l1=new Label("Attendance Management System \n Guided by- Prof Anitha Ashishdeep\n Prepared By- Karan Shah(17BCE109) \n                      Vinya Dengre(17BCE134)");
        l1.setStyle("-fx-font-size:30");
        l1.setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY,Insets.EMPTY)));
        VBox sp= myVBox();
        setBackground(sp,"C:\\Users\\rajiv\\OneDrive\\Desktop\\mp_karan\\src\\sample\\images\\s3.jpg");
        sp.getChildren().add(l1);
        Scene scene=new Scene(sp,400,400);
        window.setScene(scene);

        window.show();
    }
    public static void display(String title) throws FileNotFoundException {
        Stage window=new Stage();
        window.setMaximized(true);
        window.setTitle(title);
        Label l1=new Label("");
        l1.setAlignment(Pos.CENTER);
        l1.setStyle("-fx-font-size:20");
        l1.setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY,Insets.EMPTY)));
        if(title.equals("About School")) l1.setText("XYZ School for Children is one of the fastest growing schools in Ahmedabad mentored\n by the management team of Udgam Consultancy. The school was founded in 2014 by Shree Amthabhai \nDesai with a belief to provide quality education. He wished to serve the society by giving an equal\n learning opportunity to all children." +
                "XYZ School is a co-educational English medium school. \n It is affiliated to the Central Board of Secondary Education (CBSE). The school creates a balance \nbetween academics and extra-curricular activities. It believes in giving each student an opportunity to \nunderstand and accept his/her strengths and weaknesses and learn to the best of his/her abilities.");
        else if(title.equals("Admission")) l1.setText("Admission Procedure :\n" +
                "\n" +
                "Step 1: Admission Enquiry:\n" +
                "Visit the school admissions office. Our counsellor will get back to you to answer your questions and collect the required information.\n" +
                "Step 2: Registration\n" +
                "The parents are requested to visit the school Admission Office, fill out the registration form and purchase the school prospectus. Kindly carry one latest Passport size photo of the child and the parent along with self-attested Date of Birth certificate.\n" +
                "Step 3: School Visit & Interaction with the Principal\n" +
                "Parents / Guardians are invited to visit the school campus to understand the culture and ethos of the Institution. A meeting can be scheduled with the Principal, so parents can have an interaction with the Head of the school, ask questions and collect any information they require. \n" +
                "Step 4: Form submission\n" +
                "The parent needs to fill up the admission form and ensure all documents are in order. Kindly intimate the school in case your child requires some special assistance due to any health reasons\n" +
                " \n" +
                "List of documents required at the time of admission:\n" +
                "Evidence of having graduated from the previous class.\n" +
                "4 photographs student\n" +
                "2 parents photographs\n" +
                "Original TC from the previous school (for students seeking admission from class 2 onwards)\n" +
                "Proof of residence\n" +
                "Original Certificates may be produced at the time of admission for verification\n" +
                "Photocopies of all achievement certificates in the field of Academics, Sports, Extra Curricular activities.\n" +
                "Step 5: Payment of Fees\n" +
                "Once the above steps are satisfactorily completed parents will receive an admission offer and may pay the applicable fees to confirm admission.\n" +
                "Step 6 : Parent Orientation Program:\n" +
                "There will be a parent orientation program before the beginning of the new academic session. This program will acquaint the parents with the Vision, Mission & Ethos of the school.");
        else if(title.equals("Contact Us")) l1.setText("Contact us : \n" +
                "XYZ School For Children\n" +
                "Opp. Suyog Apartments,\n" +
                "B/h. Copper Stone,\n" +
                "Off. Thaltej - Shilaj road,\n" +
                "Thaltej, Ahmedabad-380059.\n" +
                "Phone: +917971012444\n" +
                "Email: info@XYZschool.com");
        else l1.setText("                                                                                   XYZ School provides quality education. The school ensures that each student achieves optimal academic and personal \n                                                                                  potential by providing a safe and nurturing learning environment, which instils a strong foundation for character development and \n                                                                                  lifelong learning; implementing best educational practices to engage each student in a culturally rich and challenging curriculum.");
        VBox sp=myVBox();
        sp.setAlignment(Pos.CENTER);
        setBackground(sp,"C:\\Users\\rajiv\\OneDrive\\Desktop\\mp_karan\\src\\sample\\images\\s3.jpg");
        sp.getChildren().add(l1);
        Scene scene=new Scene(sp,400,400);
        window.setScene(scene);

        window.show();
    }
    public static void StudentLogin(String username) throws FileNotFoundException {
        Stage window=new Stage();
        window.setMaximized(true);
        window.setTitle("Logged in as "+username);
        Button ViewAttandance=new Button("View Attendance");
        ViewAttandance.setFont(new Font("Cambria", 20));
        ViewAttandance.setOnAction(event -> viewResultSet(mysql.query2result("SELECT * FROM `student"+username.charAt(2)+"` WHERE `RollNo` = \""+username+"\"") ,"Attandance",true));
        VBox layout=myVBox();
        setBackground(layout,"C:\\Users\\rajiv\\OneDrive\\Desktop\\mp_karan\\src\\sample\\images\\login7.jpg");

        layout.getChildren().addAll(ViewAttandance);
        Scene scene=new Scene(layout,400,500);
        window.setScene(scene);
        window.show();
    }
    public static void FacultyLogin(String username) throws FileNotFoundException {
        Stage window=new Stage();
        window.setMaximized(true);
        window.setTitle("Logged in as "+username);
        Label day=new Label("Day ");
        day.setStyle("-fx-font-size:20");
        Label warning=new Label("");
        ChoiceBox <String> cb2=new ChoiceBox<>();
        if(username.equals("Karan")) cb2.getItems().addAll("A","B");
        else {
            cb2.getItems().addAll("C","D");
        }
        ChoiceBox <Integer> cb1=new ChoiceBox<>();
        for(int i=1;i<=30;i++) cb1.getItems().add(i);
        Button Mark=new Button("Mark Attandance");
        Mark.setFont(new Font("Cambria", 20));
        Mark.setOnAction(event -> {
            try {
                MarkAttandance(cb1.getValue(),cb2.getValue());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        HBox choicebox=myHBox();
        choicebox.getChildren().addAll(new Label("Class"),cb2,day,cb1,Mark);
        choicebox.setStyle("-fx-font-size:20");
        ChoiceBox <String> cb3=new ChoiceBox<>();
        if(username.equals("Karan")) cb3.getItems().addAll("A","B");
        else {
            cb3.getItems().addAll("C","D");
        }
        Button View=new Button("View Attandance");
        View.setFont(new Font("Cambria", 20));
        View.setOnAction(event -> viewResultSet(mysql.query2result("SELECT * FROM `student"+cb3.getValue()+"` ORDER BY `RollNo` Asc") ,"Attandance",false));
        HBox viewbox=myHBox();
        viewbox.getChildren().addAll(new Label("Class"),cb3,View);
        viewbox.setStyle("-fx-font-size:20");
        VBox layout=myVBox();
        setBackground(layout,"C:\\Users\\rajiv\\OneDrive\\Desktop\\mp_karan\\src\\sample\\images\\login7.jpg");
        layout.getChildren().addAll(choicebox,viewbox);
        Scene scene=new Scene(layout,400,500);
        window.setScene(scene);
        window.show();


    }
    public static TableView viewResultSet(ResultSet rs,String title,Boolean Return)
    {
        Stage window=new Stage();
        window.setTitle(title);
        ObservableList data= FXCollections.observableArrayList();
        TableView result=new TableView();
        try {
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                result.getColumns().addAll(col);
            }
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                {
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
                        row.add(rs.getString(i));
                    data.add(row);
                    result.setItems(data);
                }

            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        Scene scene=new Scene(result,2000,80);
        window.setScene(scene);
        if (!Return) window.setMaximized(true);
        window.show();
        return null;
    }

    public static void MarkAttandance(int i,String abcd) throws FileNotFoundException {
        Stage window=new Stage();
        window.setMaximized(true);
        Label warning =new Label("");
        window.setTitle("Marking Attandance for day "+i);
        VBox container=myVBox();
        HBox first=myHBox();
        first.getChildren().add(new Label("Roll Number"));
        first.getChildren().add(new Label("Present/Absent"));
        ScrollPane sp1=new ScrollPane();
        CheckBox [] cbs=new CheckBox[40];
        for(int t=1;t<=39;t++) cbs[t]=new CheckBox();
        for(int t=1;t<=39;t++)
        {
            HBox temp=myHBox();
            temp.getChildren().add(new Label("17"+abcd+"CE"+String.format("%03d", t)));
            temp.getChildren().add(cbs[t]);
            container.getChildren().add(temp);
        }
        sp1.setContent(container);
        VBox temp=myVBox();
        Button submit=new Button("Submit");
        submit.setFont(new Font("Cambria", 20));
        submit.setOnAction(event ->
        {
            for(int j=1;j<=39;j++)
            {
                mysql.addAttandance("17"+abcd+"CE"+String.format("%03d", j),cbs[j].isSelected(),i,abcd);
                mysql.getTotal("17"+abcd+"CE"+String.format("%03d", j),abcd);
            }
            warning.setText("Attandance Saved");
        });
        temp.getChildren().add(first);
        temp.getChildren().add(sp1);
        temp.getChildren().add(submit);
        temp.getChildren().add(warning);
        //window.setMaximized(true);
        Scene scene=new Scene(temp);
        window.setScene(scene);
        window.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}