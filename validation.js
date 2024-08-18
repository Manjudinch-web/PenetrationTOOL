// Add event listener to the validate button
document.getElementById('validateBtn').addEventListener('click', validateHTML);

function validateHTML(e) {
    e.preventDefault(); // Prevents the form from reloading the page
    const htmlInput = document.getElementById('htmlInput').value;
    const parser = new DOMParser();
    const doc = parser.parseFromString(htmlInput, 'text/html');
    const inputs = doc.querySelectorAll('input, textarea, select');
    let validationMessages = [];

    inputs.forEach(input => {
        let type = input.getAttribute('type');
        let name = input.getAttribute('name') || 'Unnamed Input';
        let issues = [];

        // General Checks
        if (input.tagName.toLowerCase() === 'input' && !input.hasAttribute('required') && ['text', 'email', 'password', 'number', 'tel', 'url', 'search'].includes(type)) {
            issues.push('Missing "required" attribute.');
        }

        // Type-Specific Checks
        switch (type) {
            case 'text':
                if (!input.hasAttribute('maxlength')) {
                    issues.push('Missing "maxlength" attribute for text input.');
                }
                break;

            case 'password':
                if (!input.hasAttribute('pattern')) {
                    issues.push('Missing "pattern" attribute for password input.');
                }
                break;

            case 'email':
                if (!input.hasAttribute('pattern')) {
                    issues.push('Missing "pattern" attribute for email input.');
                } else {
                    let pattern = input.getAttribute('pattern');
                    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(pattern)) {
                        issues.push('Invalid email pattern.');
                    }
                }
                break;

            case 'url':
                if (!input.hasAttribute('pattern')) {
                    issues.push('Missing "pattern" attribute for URL input.');
                }
                break;

            case 'number':
                if (!input.hasAttribute('min') || !input.hasAttribute('max')) {
                    issues.push('Missing "min" or "max" attributes for number input.');
                }
                break;

            case 'tel':
                if (!input.hasAttribute('pattern')) {
                    issues.push('Missing "pattern" attribute for telephone input.');
                }
                break;

            case 'range':
                if (!input.hasAttribute('min') || !input.hasAttribute('max') || !input.hasAttribute('step')) {
                    issues.push('Missing "min", "max", or "step" attributes for range input.');
                }
                break;

            case 'date':
            case 'time':
            case 'datetime-local':
            case 'month':
            case 'week':
                if (!input.hasAttribute('min') || !input.hasAttribute('max')) {
                    issues.push('Missing "min" or "max" attributes for date/time input.');
                }
                break;

            default:
                if (type) {
                    issues.push(`Unknown input type: ${type}`);
                }
        }

        // Textarea Specific Checks
        if (input.tagName.toLowerCase() === 'textarea') {
            if (!input.hasAttribute('maxlength')) {
                issues.push('Missing "maxlength" attribute for textarea.');
            }
        }

        // Select Specific Checks
        if (input.tagName.toLowerCase() === 'select' && !input.hasAttribute('required')) {
            issues.push('Missing "required" attribute for select input.');
        }

        // Check for potential HTML injection
        if (input.hasAttribute('value') && /<.*?>/.test(input.value)) {
            issues.push('Potential HTML injection detected.');
        }

        // Check for potential script injection
        if (input.hasAttribute('value') && /<script.*?>.*?<\/script>/.test(input.value)) {
            issues.push('Potential script injection detected.');
        }

        if (issues.length > 0) {
            validationMessages.push(`Input "${name}" has the following issues: ${issues.join(', ')}`);
        } else {
            validationMessages.push(`Input "${name}" is valid and sanitized.`);
        }
    });

    // Display the results
    const validationResult = document.getElementById('validationResult');
    validationResult.innerHTML = validationMessages.join('<br>');
}
 