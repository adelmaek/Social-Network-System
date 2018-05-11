import json
import random 
from pprint import pprint
import networkx as nx
try:
    import matplotlib.pyplot as plt
except:
    raise


def random_color():
    rgbl=[255,0,0]
    random.shuffle(rgbl)
    return tuple(rgbl)

with open('UsersFileKolena.json') as f:
    data = json.load(f)

graph = nx.Graph()
colors = range(20)
for i in range (len(data)):
    graph.add_node(data[i]['Users']['Username'])

for i in range (len(data)):
    name = data[i]['Users']['Username']
    for j in range (len(data[i]['Users']['Friends'])):
        graph.add_edge(data[i]['Users']['Username'],data[i]['Users']['Friends'][j])
        
nx.draw(graph,node_color='#A0CBE2',edge_color='#A0CB77',width=2,with_labels = True)
plt.savefig("graph.png") # save as png
plt.show() # display
