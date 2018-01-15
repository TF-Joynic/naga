# Daemon thread which heals bad server node.

# Healing Policy SHALL be a procedure may described as below:

 \#1 introduce limited IDEMPOTENT request (like *getXxInfoById()*) to these service node;
 
   * idempotent request replication and introduce to target ip:port;
   
   * The service node is probably having problem returning proper data to client. <br /> 
   Thus request count shall be restricted in time unit. Using Java *Semaphore* ?
   
   * As it mentioned above(*getXxInfoById()*), does is mean we need to ask
    the Service Provider to specify its (one or many) idempotent api(s)?
 
 \#2 handle successfully healed/failed node.
    
   * put healed node back to health node list;
   
   * How to handle the node which is always in unavailable status the next step. Report this to admin or else?
   
 \#3 ...