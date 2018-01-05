# *Naga Naming Service* for RPC naming lookup

<br>


## Traditional monolithic application problems:

   * Huge project volume with huge disk usage;
   * Code is becoming extremely Complex and Complicated;
   * Hard to maintence for developers;
   * Deploy system's getting slower day by day;
   * Bring down Quality of Service;
   * and so on...
    
## What we gotta to do ?
   * Redefine modules and Separate 'em;
   * Deploy on different machines and Using RPC between client and server;
  
    
## Now see what we have：
   
   * Config file in your client side:
   ```	
		[user_center_hosts]
   		192.168.1.100:1234,
		192.168.1.101:1234,
		192.168.1.102:1234,
		...
   ```

    
## After some time, the traffic of your site increased drasticly for some reason.
   * One day you informed the operation and maintenance staff of your company </br> that you wanna deploy another 100 user center services online, and do not forget to modify the config file BTW.

   * Then the config file may now look like this(What a <b>TORMENT</b> to the operation and maintenance staff<br /> for they must edit this file very carefully.)：
   ``` 
		[user_center_hosts]
   		192.168.1.100:1234,
		192.168.1.101:1234,
		192.168.1.102:1234,
		192.168.1.103:1234,
		192.168.1.104:1234,
		192.168.1.105:1234,
		192.168.1.106:1234,
		192.168.1.107:1234,
		192.168.1.108:1234,
		192.168.1.109:1234,
		192.168.1.110:1234,
		192.168.1.111:1234,
		192.168.1.111:1234,
		...(so many config code lines)
		
   ```

## Then Christmas is comming and one of your service machine(101) is not working well for lack of maintence. :( 
  *  ~~192.168.1.101:1234,~~
		
  * Unfortunately the config file is modified manually and redeploy for another time.

  * After deploy is done, you are about to opening the presents. In the meantime your just received massive alarm emails<br /> that indicate service machine(106) is shutdown unexpectedly.

  * ...

  * What a horrible X'mas!!!
 

<br />
# Now let's try *Naga Naming Service* 
  * service name and service hosts mapping:

	```
                                                    
										192.168.1.100:1234,
										192.168.1.101:1234,
										192.168.1.102:1234,
		user_center_hosts	---------   192.168.1.103:1234,
										192.168.1.104:1234,
										192.168.1.105:1234,
										192.168.1.106:1234,
										192.168.1.107:1234,
										192.168.1.108:1234
										...

                      
    ```
	

  * Automatically detect naughty service machine and evict it from service list until it behaves.
  * Admin pages that can show status of all service machine and make it easy to execute daily maintenance.
  * and more...