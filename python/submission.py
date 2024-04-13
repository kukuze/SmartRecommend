import matplotlib

matplotlib.use('TkAgg')
import matplotlib.pyplot as plt
import numpy as np
#0.7678571428571429
# Setting global font size for all text in the plot
plt.rcParams.update({'font.size': 26})  # Increase global font size
plt.rc('font', family='Times New Roman')
# Datasi
years = ['2020', '2021', '2022', '2023']
submission_counts = [15324, 20644, 19635, 36046]

# Plot
plt.figure(figsize=(12, 9))
color1 = (25 / 255, 144 / 255, 206 / 255)  # Specified blue color

# Plot bars with the specified blue color
bars = plt.bar(years, submission_counts, color=color1, width=0.3)  # Set width to 0.6
plt.xlabel('Year')
plt.ylabel('Total Submissions')

# Adding numbers on top of each bar
for bar in bars:
    yval = bar.get_height()
    plt.text(bar.get_x() + bar.get_width() / 2, yval, f'{yval / 1000:.1f}k', ha='center', va='bottom')

# Customize y-axis to show 'k' for thousands
ax = plt.gca()  # Get current axis
ax.yaxis.set_major_formatter(matplotlib.ticker.FuncFormatter(lambda x, p: f'{int(x / 1000)}k'))

plt.tight_layout()

# Save the figure
plt.savefig('./submission.png')

plt.show()
