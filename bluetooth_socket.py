#!/usr/bin/env python
# -*- coding: utf-8 -*-

'''****************************************************************************
* File Name: bluetooth_socket.py                                              *
* Purpose:   Short script to connect and listen to commands from a bluetooth  *
*            device on a raspberry pi.                                        *
* Date:      04/05/2020                                                       *
* Code derived from bluez example sourced from mit.edu.                       *
* Dev note: your bluetooth device must be PAIRED before using this script.    * 
****************************************************************************'''

################################################################################
#################################### version ###################################
__version__ = "1.0.0"

################################################################################
#################################### imports ###################################
import bluetooth 

################################################################################
##################################### main #####################################

print("script start")
# initialize the bluetooth socket 
server_sock = bluetooth.BluetoothSocket(bluetooth.RFCOMM)
port = 1
server_sock.bind(("", port))

print("awaiting connection.")
# here we wait for any sort of initial probe from the client
server_sock.listen(1)

# accept the new connection 
client_sock,addr = server_sock.accept()
print("Accepted connection from", addr)

# now that the connection is open, we await a response and (for now) print it
print("waiting for response")
data = client_sock.recv(1024)
print(data)

# then terminate the connection
client_sock.close()
server_sock.close()
print("Script finished")
