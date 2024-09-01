# order service

# Step 1: 
Please wait about 6 minutes because we embed the full environment (maven,..)
</br>
Please run command in call_billing folder. (cd order_service)
</br>
run command : docker build -t order_service .

# Step 2:
run command: docker run -p 8080:8080 order_service

# Step 3:
access: http://127.0.0.1:8080/swagger-ui.html

# Step 4:
Test
</br>
# NOTE:
I used sql server. You can change config values in application-dev.yml 
