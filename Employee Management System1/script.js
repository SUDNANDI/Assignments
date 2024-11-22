class Employee {
    constructor(name, empId, skill, doj, department) {
        this.name = name;
        this.empId = empId;
        this.skill = skill;
        this.doj = new Date(doj);
        this.department = department;
    }

    calculateExperience() {
        const currentDate = new Date();
        const experienceInMs = currentDate - this.doj;
        const experienceInYears = Math.floor(experienceInMs / (1000 * 60 * 60 * 24 * 365));
        return experienceInYears;
    }
}

const employees = [];

document.getElementById('employee-form').addEventListener('submit', function (e) {
    e.preventDefault();
    
    const name = document.getElementById('name').value;
    const empId = document.getElementById('empId').value;
    const skill = document.getElementById('skill').value;
    const doj = document.getElementById('doj').value;
    const department = document.getElementById('department').value;

    const employee = new Employee(name, empId, skill, doj, department);
    employees.push(employee);

    alert('Employee added successfully!');
    this.reset();
});

document.getElementById('search-btn').addEventListener('click', function () {
    const searchId = document.getElementById('search-id').value;
    const employee = employees.find(emp => emp.empId === searchId);

    if (employee) {
        const experience = employee.calculateExperience();
        document.getElementById('employee-details').innerHTML = `
            <h3>Employee Details:</h3>
            <p><strong>Name:</strong> ${employee.name}</p>
            <p><strong>Employee ID:</strong> ${employee.empId}</p>
            <p><strong>Skill:</strong> ${employee.skill}</p>
            <p><strong>Date of Joining:</strong> ${employee.doj.toLocaleDateString()}</p>
            <p><strong>Department:</strong> ${employee.department}</p>
            <p><strong>Experience:</strong> ${experience} years</p>
        `;
    } else {
        document.getElementById('employee-details').innerHTML = `<p>No employee found with ID: ${searchId}</p>`;
    }
});

document.getElementById('remove-btn').addEventListener('click', function () {
    const searchId = document.getElementById('search-id').value;
    const employeeIndex = employees.findIndex(emp => emp.empId === searchId);

    if (employeeIndex !== -1) {
        employees.splice(employeeIndex, 1);
        alert('Employee removed successfully!');
        document.getElementById('employee-details').innerHTML = '';
    } else {
        alert('No employee found with this ID to remove.');
    }
});
