document.getElementById('emi-form').addEventListener('submit', function(event) {
    event.preventDefault();

    const loanType = document.getElementById('loan-type').value;
    const loanAmount = parseFloat(document.getElementById('loan-amount').value);
    const salary = parseFloat(document.getElementById('salary').value);

    let interestRate, maxDuration;

    // Loan type interest rates
    if (loanType === 'home') {
        interestRate = 0.07;
    } else if (loanType === 'car') {
        interestRate = 0.10;
    } else if (loanType === 'education') {
        interestRate = 0.08;
    }

    // Suggesting loan duration based on salary
    if (salary < 30000) {
        maxDuration = 5; // 5 years for low salary
    } else if (salary >= 30000 && salary < 70000) {
        maxDuration = 10; // 10 years for mid-range salary
    } else {
        maxDuration = 20; // 20 years for high salary
    }

    // Calculate EMI
    const ratePerMonth = interestRate / 12;
    const months = maxDuration * 12;
    const emi = (loanAmount * ratePerMonth * Math.pow(1 + ratePerMonth, months)) / (Math.pow(1 + ratePerMonth, months) - 1);

    // Display result
    document.getElementById('emi').textContent = emi.toFixed(2);
    document.getElementById('duration').textContent = maxDuration;

    document.getElementById('emi-result').style.display = 'block';
});
