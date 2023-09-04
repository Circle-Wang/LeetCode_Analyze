# import tsplib95

from colony import Colony
from solver import Solver

problem = tsplib95.load_problem('bays29.tsp')
G = problem.get_graph()

solver = Solver()
colony = Colony(1, 3)
sales = 5

ans = solver.solve(G, colony, sales, start=13, limit=50, opt2=20)
print(sum(s.cost for s in ans), ans)
