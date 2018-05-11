import json
import randomcolor
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

with open('UsersFileKolena.json',encoding="utf8") as f:
    data = json.load(f)

graph = nx.Graph()
colors = range(20)
for i in range (len(data)):
    graph.add_node(data[i]['Users']['Username'])

for i in range (len(data)):
    name = data[i]['Users']['Username']
    for j in range (len(data[i]['Users']['Friends'])):
        graph.add_edge(data[i]['Users']['Username'],data[i]['Users']['Friends'][j])

for i in range (len(nx.nodes(graph))):
    rand_color = randomcolor.RandomColor()
    
nx.draw(graph,node_color='#4da6ff',font_size=10,node_size=500,edge_color='#99d6ff',width=0.5,with_labels = True)
plt.savefig("graph.png") # save as png
plt.show() # display
