import os as os 

os.chdir("TM2_all_two_layer")

for i in range(0,311):
    os.mkdir("CrossSection" + str(i))
    
#for i in range(1,311):
#    os.rmdir("CrossSection" + str(i))