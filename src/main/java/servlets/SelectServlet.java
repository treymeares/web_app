package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@WebServlet(name = "SelectServlet", value = "/SelectServlet")
public class SelectServlet extends HttpServlet {


    private Connection connection = null;
    String url = "jdbc:mysql://localhost:3306/hospital_system"; // database connection url
    String password = "gpottk9t"; // database password
    String username = "root"; // database username

    // doGet method on servlet. If the submitted from has method = "get", this will be called.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // we call doPost() to avoid duplicate code
        doPost(request, response);
        //String destination = "output.jsp";

    }

    // doPost method on servlet. If the submitted from has method = "post", this will be called.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String date1 = request.getParameter("date1");
        String date2 = request.getParameter("date2");
        String date3 = request.getParameter("date3");
        String date4 = request.getParameter("date4");
        String patient = request.getParameter("patient");
        String patient1 = request.getParameter("patient1");
        String treatment = request.getParameter("treatment");
        String time1 = request.getParameter("time1");
        String time2 = request.getParameter("time2");
        String time3 = request.getParameter("time3");
        String time4 = request.getParameter("time4");
        String doctor = request.getParameter("doctor");
        String doctor1 = request.getParameter("doctor1");

        //send the info to the jsp output page for formatting
        String destination = "output.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);

        // here you put your queries
        List<String> queries = new ArrayList<>();
        queries.add(0, "SELECT patients.firstname, patients.lastname, patients.room_number, \n" +
                "admitted_patients.arrival_date\n" +
                "FROM admitted_patients, patients\n" +
                "WHERE patients.patient_id = admitted_patients.patient_id\n" +
                "AND discharge_date IS NULL;");
        queries.add(1, "SELECT *\n" +
                "FROM (VALUES ROW (1) , ROW (2) , ROW (3), ROW (4), ROW (5), ROW (6), ROW \n" +
                "(7), ROW (8),\n" +
                "ROW (9), ROW (10), ROW (11), ROW (12), ROW (13), ROW (14), ROW (15), ROW \n" +
                "(16), ROW (17),\n" +
                "ROW (18), ROW(19), ROW (20)) R(room_number)\n" +
                "WHERE NOT EXISTS (\n" +
                "SELECT room_number\n" +
                "FROM patients, admitted_patients\n" +
                "WHERE patients.room_number = R.room_number\n" +
                "AND patients.patient_id = admitted_patients.patient_id\n" +
                "AND discharge_date IS NULL);");
        queries.add(2, "SELECT patients.firstname, patients.lastname, patients.room_number, \n" +
                "admitted_patients.arrival_date\n" +
                "FROM admitted_patients, patients\n" +
                "WHERE patients.patient_id = admitted_patients.patient_id\n" +
                "AND discharge_date IS NULL\n" +
                "UNION\n" +
                "SELECT *, null, null, null\n" +
                "FROM (VALUES ROW (1) , ROW (2) , ROW (3), ROW (4), ROW (5), ROW (6), ROW \n" +
                "(7), ROW (8),\n" +
                "ROW (9), ROW (10), ROW (11), ROW (12), ROW (13), ROW (14), ROW (15), ROW \n" +
                "(16), ROW (17),\n" +
                "ROW (18), ROW(19), ROW (20)) R(room_number)\n" +
                "WHERE NOT EXISTS (\n" +
                "SELECT room_number\n" +
                "FROM patients, admitted_patients\n" +
                "WHERE patients.room_number = R.room_number\n" +
                "AND patients.patient_id = admitted_patients.patient_id\n" +
                "AND discharge_date IS NULL);");
        queries.add(3, "SELECT firstname, lastname, insurance_id, emergency_contact_id, \n" +
                "room_number\n" +
                "FROM patients;");
        queries.add(4, "SELECT patients.patient_id, patients.firstname, patients.lastname\n" +
                "FROM admitted_patients, patients\n" +
                "WHERE patients.patient_id = admitted_patients.patient_id\n" +
                "AND discharge_date IS NULL;");
        queries.add(5, "SELECT DISTINCT patients.patient_id, patients.firstname, patients.lastname\n" +
                "FROM admitted_patients, patients\n" +
                "WHERE patients.patient_id = admitted_patients.patient_id\n" +
                "AND discharge_date BETWEEN '" + date1 + " " + time1 + "' and '" + date2 + " " + time2 + "';");
        queries.add(6, "SELECT DISTINCT patients.patient_id, patients.firstname, patients.lastname\n" +
                "FROM admitted_patients, patients\n" +
                "WHERE patients.patient_id = admitted_patients.patient_id\n" +
                "AND arrival_date BETWEEN '" + date3 + " " + time3 + "' and '" + date4 + " " + time4 + "';");
        queries.add(7, "SELECT admitted_patients.admit_id, admitted_patients.initial_diagnosis\n" +
                "FROM admitted_patients, patients\n" +
                "WHERE patients.lastname = '" + patient + "'\n" +
                "AND patients.patient_id = admitted_patients.patient_id;");
        queries.add(8, "SELECT a.arrival_date, t.treatment_name, t.timestamp FROM admitted_patients a\n" +
                "INNER JOIN patients p USING (patient_id)\n" +
                "INNER JOIN treatments t USING (patient_id)\n" +
                "WHERE (p.lastname = '" + patient1 + "') ORDER BY a.arrival_date DESC, t.timestamp ASC;");
        queries.add(9, "SELECT patients.patient_id, patients.firstname, patients.lastname, \n" +
                "admitted_patients.initial_diagnosis, employees.firstname, \n" +
                "employees.lastname\n" +
                "FROM employees, patients, admitted_patients\n" +
                "WHERE (arrival_date - 30) <= discharge_date\n" +
                "AND admitted_patients.patient_id = patients.patient_id\n" +
                "AND patients.employee_id = employees.employee_id;");
        queries.add(10, "SELECT patients.patient_id, COUNT(*) AS admissions\n" +
                "FROM patients, admitted_patients\n" +
                "WHERE admitted_patients.patient_id = patients.patient_id\n" +
                "GROUP BY patient_id;");
        queries.add(11, "SELECT P.lastName, initial_diagnosis, Count(initial_diagnosis)\n" +
                "FROM admitted_patients A JOIN patients P USING (patient_id) \n" +
                "GROUP BY lastName, initial_diagnosis\n" +
                "ORDER BY Count(initial_diagnosis) DESC;");
        queries.add(12, "SELECT P.lastName, initial_diagnosis, Count(initial_diagnosis)\n" +
                "FROM admitted_patients A JOIN patients P USING (patient_id) \n" +
                "WHERE discharge_date IS NULL \n" +
                "GROUP BY lastName, initial_diagnosis\n" +
                "ORDER BY Count(initial_diagnosis) DESC;");
        queries.add(13, "SELECT treatments.treatment_name, COUNT(*)\n" +
                "FROM treatments, admitted_patients\n" +
                "WHERE treatments.patient_id = admitted_patients.patient_id\n" +
                "AND discharge_date IS NULL\n" +
                "GROUP BY treatment_name\n" +
                "ORDER BY COUNT(*) DESC;");
        queries.add(14, "SELECT admitted_patients.initial_diagnosis\n" +
                "FROM admitted_patients, patients\n" +
                "WHERE patients.patient_id = admitted_patients.patient_id\n" +
                "GROUP BY initial_diagnosis\n" +
                "HAVING MAX(admit_id);");
        queries.add(15, "SELECT patients.firstname, patients.lastname, employees.firstname, \n" +
                "employees.lastname\n" +
                "FROM patients, employees, treatments\n" +
                "WHERE patients.patient_id = treatments.patient_id\n" +
                "AND employees.employee_id = treatments.employee_id\n" +
                "AND treatments.treatment_id = " + treatment + ";");
        queries.add(16, "SELECT employees.lastname, employees.firstname, employees.category\n" +
                "FROM employees\n" +
                "ORDER BY lastname ASC;");
        queries.add(17, "SELECT DISTINCT employees.firstname, employees.lastname\n" +
                "FROM employees, patients, admitted_patients\n" +
                "WHERE patients.employee_id = employees.employee_id\n" +
                "AND patients.patient_id = admitted_patients.patient_id\n" +
                "GROUP BY(admitted_patients.patient_id) HAVING COUNT(admit_id) >= 4;");
        queries.add(18, "SELECT admitted_patients.initial_diagnosis, COUNT(*)\n" +
                "FROM admitted_patients, employees, patients\n" +
                "WHERE employees.lastname = '" + doctor + "'\n" +
                "AND patients.employee_id = employees.employee_id\n" +
                "AND patients.patient_id = admitted_patients.patient_id\n" +
                "GROUP BY initial_diagnosis\n" +
                "ORDER BY COUNT(*) DESC;");
        queries.add(19, "SELECT treatments.treatment_name, COUNT(*)\n" +
                "FROM treatments, employees\n" +
                "WHERE employees.lastname = '" + doctor1 + "'\n" +
                "AND treatments.employee_id = employees.employee_id\n" +
                "GROUP BY treatment_name\n" +
                "ORDER BY COUNT(*) DESC;");
        queries.add(20, "SELECT DISTINCT employees.firstname,  employees.lastname\n" +
                "FROM treatments, employees\n" +
                "WHERE treatments.employee_id = employees.employee_id;");

        // get values from form, focus on the name of select field -> name="select_sql"
        int value_from_form = Integer.parseInt(request.getParameter("select_sql"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            PrintWriter out = response.getWriter();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(queries.get(value_from_form));
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            // first loop to get all tuples
            int count = 1 ; // start counting from 1 always
            String[] columnNames = new String[columnCount];
            ArrayList<String> columnContents = new ArrayList<String>(50);
            while(count<=columnCount) {
                columnNames[count - 1] = resultSetMetaData.getColumnLabel(count);
                count++;
            }
            String joinedString = String.join(" , ", columnNames);
            out.println(joinedString);
            request.setAttribute("headers", columnNames);
            String contnets = "";

            while(resultSet.next()) {
                // second loop to get all columns in each tuple
                for(int i=1; i<=columnCount; i++) {
                    String column_title = resultSetMetaData.getColumnName(i);
                    // Data type object since the columns can be int, string, or datetime
                    Object column_value = resultSet.getObject(column_title);
                    if (column_value != null) {
                        String cv = column_value.toString();
                        columnContents.add(cv);
                    }
                    else{
                        String cv = "null";
                        columnContents.add(cv);
                    }
                }
                contnets += ";";
            }
            request.setAttribute("columnCount", columnCount);
            request.setAttribute("contents", columnContents);
            System.out.println(columnContents);
            out.println("");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        // get the name of the query for the jsp page
        if(value_from_form == 0) {
            request.setAttribute("query", "1.1. List the rooms that are occupied, along with the associated patient names and the date the patient was admitted");
        }
        else if(value_from_form == 1) {
            request.setAttribute("query", "1.2. List the rooms that are currently unoccupied.");
        }
        else if(value_from_form == 2) {
            request.setAttribute("query", "1.3. List all rooms in the hospital along with patient names and admission dates for those that are occupied.");
        }
        else if(value_from_form == 3) {
            request.setAttribute("query", "2.1. List all patients in the database, with full personal information.");
        }
        else if(value_from_form == 4) {
            request.setAttribute("query", "2.2. List all patients currently admitted to the hospital. List only patient identification number and name.");
        }
        else if(value_from_form == 5) {
            request.setAttribute("query", "2.3. List all patients who were discharged in a given date range. List only patient identification number and name.");
        }
        else if(value_from_form == 6) {
            request.setAttribute("query", "2.4. List all patients who were admitted within a given date range. List only patient identification number and name");
        }
        else if(value_from_form == 7) {
            request.setAttribute("query", "2.5. For a given patient (either patient identification number or name), list all admissions to the hospital along with the diagnosis for each admission.");
        }
        else if(value_from_form == 8) {
            request.setAttribute("query", "2.6. For a given patient (either patient identification number or name), list all treatments that were administered. Group treatments by admissions. List admissions in descending chronological order, and list treatments in ascending chronological order within each admission.");
        }
        else if(value_from_form == 9) {
            request.setAttribute("query", "2.7. List patients who were admitted to the hospital within 30 days of their last discharge date. For each patient list their patient identification number, name, diagnosis, and admitting doctor.");
        }
        else if(value_from_form == 10) {
            request.setAttribute("query", "2.8. For each patient that has ever been admitted to the hospital, list their total number of admissions, average duration of each admission, longest span between admissions, shortest span between admissions, and average span between admissions.");
        }
        else if(value_from_form == 11) {
            request.setAttribute("query", "3.1. List the diagnoses given to patients, in descending order of occurrences. List diagnosis identification number, name, and total occurrences of each diagnosis.");
        }
        else if(value_from_form == 12) {
            request.setAttribute("query", "3.2. List the diagnoses given to hospital patients, in descending order of occurrences. List diagnosis identification number, name, and total occurrences of each diagnosis.");
        }
        else if(value_from_form == 13) {
            request.setAttribute("query", "3.3. List the treatments performed on admitted patients, in descending order of occurrences. List treatment identification number, name, and total number of occurrences of each treatment.");
        }
        else if(value_from_form == 14) {
            request.setAttribute("query", "3.4. List the diagnoses associated with patients who have the highest occurrences of admissions to the hospital, in ascending order or correlation.");
        }
        else if(value_from_form == 15) {
            request.setAttribute("query", "3.5. For a given treatment occurrence, list the patient name and the doctor who ordered the treatment.");
        }
        else if(value_from_form == 16) {
            request.setAttribute("query", "4.1. List all workers at the hospital, in ascending last name, first name order. For each worker, list their, name, and job category.");
        }
        else if(value_from_form == 17) {
            request.setAttribute("query", "4.2. List the primary doctors of patients with a high admission rate (at least 4 admissions within a one-year time frame).");
        }
        else if(value_from_form == 18) {
            request.setAttribute("query", "4.3. For a given doctor, list all associated diagnoses in descending order of occurrence. For each diagnosis, list the total number of occurrences for the given doctor.");
        }
        else if(value_from_form == 19) {
            request.setAttribute("query", "4.4. For a given doctor, list all treatments that they ordered in descending order of occurrence. For each treatment, list the total number of occurrences for the given doctor.");
        }
        else{
            request.setAttribute("query", "4.5. List employees who have been involved in the treatment of every admitted patient.");
        }
        requestDispatcher.forward(request, response);
    }

}