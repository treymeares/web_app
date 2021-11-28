<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<style>

    body {

    }
    input[type=text], select {
        width: 100%;
        padding: 14px 20px;
        margin: 8px 0;
        display: inline-block;
        border: 2px solid #878787;
        border-radius: 2px;
        box-sizing: border-box;
    }

    input[type=submit] {
        width: 100%;
        background-color: #4CAF50;
        color: white;
        padding: 14px 20px;
        margin: 8px 0;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    input[type=submit]:hover {
        background-color: #45a049;
    }


    div {
        border-radius: 5px;
        background-color: #b0b0b0;
        padding: 20px;
        box-sizing: border-box;

    }

    h1 {
        text-align: center;
        text-transform: uppercase;
        color: #4CAF50;
        font-size: 30px;
    }
    h2 {
        text-align: left;
        text-transform: uppercase;
        color: #4CAF50;
        font-size: 16px;
    }

    p {
        text-indent: 0px;
        text-align: left;
        letter-spacing: 3px;
        font-size: 14px;
        box-sizing: border-box;
    }
    a {
        text-decoration: none;
        color: #008CBA;
    }

</style>
<head>
    <link rel="icon" href="hospital.jpg">
    <title>HDBM</title>
</head>
<body>
<h1>HOSPITAL DATABASE MANAGEMENT</h1>
<br/>
<%-- Before getting started, make sure to install Tomcat from https://tomcat.apache.org/ --%>
<%-- For windows, Tomcat will be installed at C:/->Program Files->Apache Software  Foundation -> Tomcat x.xx --%>
<%--Once installed, add Tomcat configuration in IntelliJ, follow https://learntocodetogether.com/configure-tomcat-server-on-intellij-idea/ --%>


<%-- A simple form that provides SQL options and redirects to SelectServlet --%>
<form name = "myForm" action="SelectServlet" method="post"> <%-- Notice, mathod = POST, doPost() method is called on form submit --%>

    <script type="text/javascript">
        function showfield(name){
            if (name==='5')document.getElementById('div1').innerHTML='Beginning Date (Date Format: YYYY-MM-DD): <input type="text" name="date1" placeholder="YYYY-MM-DD" /> Beginning Time (Time Format: 08:00): <input type="text" name="time1" placeholder="08:00" />Ending Date (Date Format: YYYY-MM-DD): <input type="text" name="date2" placeholder="YYYY-MM-DD" /> Ending Time (Time Format: 08:00): <input type="text" name="time2" placeholder="08:00"  />';
            else if (name==='6')document.getElementById('div1').innerHTML='Beginning Date (Date Format: YYYY-MM-DD): <input type="text" name="date3" placeholder="YYYY-MM-DD" /> Beginning Time (Time Format: 08:00): <input type="text" name="time3" placeholder="08:00"  /> Ending Date (Date Format: YYYY-MM-DD): <input type="text" name="date4" placeholder="YYYY-MM-DD" />  Ending Time (Time Format: 08:00): <input type="text" name="time4" placeholder="08:00"  />';
            else if (name==='7')document.getElementById('div1').innerHTML='Patient Last Name: <input type="text" name="patient" />';
            else if(name==='8')document.getElementById('div1').innerHTML='Patient Last Name: <input type="text" name="patient1" />';
            else if(name==='15')document.getElementById('div1').innerHTML='Treatment Occurrence: <input type="text" name="treatment" />';
            else if(name==='18')document.getElementById('div1').innerHTML='Select Doctor: <input type="text" name="doctor" />';
            else if(name==='19')document.getElementById('div1').innerHTML='Type Doctor Last Name: <input type="text" name="doctor1" />';
            else document.getElementById('div1').innerHTML='';
        }

        function getSelectedLabel(sel) {
            document.getElementById("selectedLabel").value = sel.options[sel.selectedIndex].text;
            document.form1.submit();
        }
    </script>
    <input type="hidden" name="selectedLabel" id="selectedLabel">
    <select name="select_sql" id="mySelect" hidden = "text" onchange="showfield(this.options[this.selectedIndex].value)">
        <option value="">Please Select A Query</option>
        <option value="0">1.1. List the rooms that are occupied, along with the associated patient names and the date the patient was admitted.</option>
        <option value="1">1.2. List the rooms that are currently unoccupied.</option>
        <option value="2">1.3. List all rooms in the hospital along with patient names and admission dates for those that are occupied.</option>
        <option value="3">2.1. List all patients in the database, with full personal information.</option>
        <option value="4">2.2. List all patients currently admitted to the hospital. List only patient identification number and name.</option>
        <option value="5">2.3. List all patients who were discharged in a given date range. List only patient identification number and name.</option>
        <option value="6">2.4. List all patients who were admitted within a given date range. List only patient identification number and name.</option>
        <option value="7">2.5. For a given patient (either patient identification number or name), list all admissions to the hospital along with the diagnosis for each admission.</option>
        <option value="8">2.6. For a given patient (either patient identification number or name), list all treatments that were administered. Group treatments by admissions. List admissions in descending chronological order, and list treatments in ascending chronological order within each admission.</option>
        <option value="9">2.7. List patients who were admitted to the hospital within 30 days of their last discharge date. For each patient list their patient identification number, name, diagnosis, and admitting doctor.</option>
        <option value="10">2.8. For each patient that has ever been admitted to the hospital, list their total number of admissions, average duration of each admission, longest span between admissions, shortest span between admissions, and average span between admissions.</option>
        <option value="11">3.1. List the diagnoses given to patients, in descending order of occurrences. List diagnosis identification number, name, and total occurrences of each diagnosis.</option>
        <option value="12">3.2. List the diagnoses given to hospital patients, in descending order of occurrences. List diagnosis identification number, name, and total occurrences of each diagnosis.</option>
        <option value="13">3.3. List the treatments performed on admitted patients, in descending order of occurrences. List treatment identification number, name, and total number of occurrences of each treatment.</option>
        <option value="14">3.4. List the diagnoses associated with patients who have the highest occurrences of admissions to the hospital, in ascending order or correlation.</option>
        <option value="15">3.5. For a given treatment occurrence, list the patient name and the doctor who ordered the treatment.</option>
        <option value="16">4.1. List all workers at the hospital, in ascending last name, first name order. For each worker, list their, name, and job category.</option>
        <option value="17">4.2. List the primary doctors of patients with a high admission rate (at least 4 admissions within a one-year time frame).</option>
        <option value="18">4.3. For a given doctor, list all associated diagnoses in descending order of occurrence. For each diagnosis, list the total number of occurrences for the given doctor.</option>
        <option value="19">4.4. For a given doctor, list all treatments that they ordered in descending order of occurrence. For each treatment, list the total number of occurrences for the given doctor.</option>
        <option value="20">4.5. List employees who have been involved in the treatment of every admitted patient.</option>
    </select>
    <div id="div1"></div>
    <input type="submit" value="SUBMIT" />


</form>

<div>
<h1>QUERY LIST</h1>

<h2>//Room Utilization//</h2>

<p>1.1. List the rooms that are occupied, along with the associated patient names and the date the patient was admitted.</p>
<p>1.2. List the rooms that are currently unoccupied.</p>
<p>1.3. List all rooms in the hospital along with patient names and admission dates for those that are occupied.</p>

<h2>//Patient Information//</h2>
<p>2.1. List all patients in the database, with full personal information.</p>
<p>2.2. List all patients currently admitted to the hospital. List only patient identification number and name.</p>
<p>2.3. List all patients who were discharged in a given date range. List only patient identification number and name.</p>
<p>2.4. List all patients who were admitted within a given date range. List only patient identification number and name.</p>
<p>2.5. For a given patient (either patient identification number or name), list all admissions to the hospital along with the diagnosis for each admission.</p>
<p>2.6. For a given patient (either patient identification number or name), list all treatments that were administered. Group treatments by admissions. List admissions in descending chronological order, and list treatments in ascending chronological order within each admission.</p>
<p>2.7. List patients who were admitted to the hospital within 30 days of their last discharge date. For each patient list their patient identification number, name, diagnosis, and admitting doctor.</p>
<p>2.8. For each patient that has ever been admitted to the hospital, list their total number of admissions, average duration of each admission, longest span between admissions, shortest span between admissions, and average span between admissions.</p>

<h2>//Diagnosis and Treatment//</h2>
<p>3.1. List the diagnoses given to patients, in descending order of occurrences. List diagnosis identification number, name, and total occurrences of each diagnosis.</p>
<p>3.2. List the diagnoses given to hospital patients, in descending order of occurrences. List diagnosis identification number, name, and total occurrences of each diagnosis.</p>
<p>3.3. List the treatments performed on admitted patients, in descending order of occurrences. List treatment identification number, name, and total number of occurrences of each treatment.</p>
<p>3.4. List the diagnoses associated with patients who have the highest occurrences of admissions to the hospital, in ascending order or correlation.</p>
<p>3.5. For a given treatment occurrence, list the patient name and the doctor who ordered the treatment.</p>

<h2>//Employee Information//</h2>
<p>4.1. List all workers at the hospital, in ascending last name, first name order. For each worker, list their, name, and job category.</p>
<p>4.2. List the primary doctors of patients with a high admission rate (at least 4 admissions within a one-year time frame).</p>
<p>4.3. For a given doctor, list all associated diagnoses in descending order of occurrence. For each diagnosis, list the total number of occurrences for the given doctor.</p>
<p>4.4. For a given doctor, list all treatments that they ordered in descending order of occurrence. For each treatment, list the total number of occurrences for the given doctor.</p>
<p>4.5. List employees who have been involved in the treatment of every admitted patient.</p>

</div>

</body>
</html>

